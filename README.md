# Make V.2.0 
<b>-- I'm doing this documentation please be patient. It is a bit large. --</b><br>
A framework that can make easily a lot of entity or objects to use in unit tests or populate a data base.

## Code Example

```<java>
MyObject myObject = MakeEntity.make(MyObject .class);
List<MyObject> myObjects = MakeEntity.makes(MyObject .class, amount);
```
## Motivation
Working in a simple project I was working with JSR303 specification in my entities. The project started with JUnit tests.<br>
While it was a small project with few entities, 4 or 5 it was easy to keep.<br>
When the project got big and I had to change the relation between my entities, my happy day became a week of hell.<br>
I visualized two sad options: remove the hibernate validator or remake all entities in my units tests.<br>
For me, as a developer, I chose the second. I was happy with the validation in JPA2 and JSF2 forms and a lot of bugs avoided with JSR303.<br>
For my boss, as a manager, time is money and spend a lot of time in test code is not acceptable.<br>
When my coworkers realized the big refactoring in all those codes. They went to the manager side. I was alone :(<br>
So I had the idea to make this framework and we decided to remove the JUnit tests until it get ready.<br>
Yes is true. I started to work with it in my house at nights. Worth it, do not worry. I got a university specialization using it;).<br>
When I put it into our application I realized that also I could used to populate my test data base. I could see a data base with 100.000 objects created in 3 hours. You must realize that I am including the relation between objects is also included in this amount. Our 50 tables were crowded with a lot of valid data.<br>
Now is in second version available for all because I worked in my house, not in my job. ;)

## Requirements
The object to be made needs to have a default constructor. A constructor without arguments.<br>
Make will create all fields for the object, if the field is another complex object is necessary that object has a default constructor too or null will be set.

## Mechanics
Objects are defined with fields. Usually is necessary to define some rules in the fields. Like a car cannot have -3.5 of gasoline and cannot pass tha maximum amount in its fuel tank.<br>
Make can create those values respecting all rules.<br>
If you use JSR303 annotations Make will read them and use to create the fields values. Make will read the JSR303 annotations using to do a reverse engineering to create valid values for each field. With the only exception about the annotation @Pattern. For this annotation you will have to create a value. A good thing is that  Make has a lot of tools to help you.<br>
All values created using the JSR303 will be valid, but some of them cannot be acceptable. Like a field that represents a birth date for a person. You can use the annotation @NotNull with @Past. In this case the values will be in the past therefore be valid. But Make can create dates like one day ago or 200 years ago and for the business will be more satisfactory some dates between 18 and 80 years. For situations like that Make has space to put a engine to create those values. This engine is called Specialized Factories.
If you do not use JSR303 annotations Make will create open values for all fields. You can change this behavior using the Specialized Factories too.

## Setup
The configuration of the framework is made with a XML setup file. Is very important that you put this file in your project test resource folder.<br>
Make uses this name for XML setup file by default but you can change it. If you do, before start to create objects with Make you need to call a static method at Factory class called loadSetup as the name of XML setup file as parameter:<br>

```<java>
 Factory.loadSetup("myXMLSetup.xml");
```

You can get a XML setup file example in src/test/resource folder from Make project.<br>
From now I will call it make.xml. Let's take a look in the make.xml file.<br>
The make.xml is divided in 3 parts:<br>
<b>Setup Tag </b><br>
```<XML>
  <setup>
    <JSR303 value="read" /><!-- read or ignore -->
    <Null value="never" /><!-- never some all -->
  </setup>
```
The JSR303 tag is used to Make setup to read or to ignore the annotations JSR303. If your project does not have these annotations, you must set ignore here.<br>
*   READ: Make will be looking for JSR303 annotations to create valid values.
*   IGNORE: Make wont be looking for JSR303 and the values will be open to any value that the field can handle.<br>

The Null tag determines how the framework will work about to set null values:<br>
*   NEVER: Make always will create values for all fields. Three cases can set null at a field: the field type does not have default constructor, JSR303 is defined to read and the field has the annotation @Null, and fields that will be treated with specialized factory.<br>
*   SOME: Make will create a value to set or will set null. Fields that will be treated with specialized factory will not be interfered for this tag. If the tag JSR303 is defined to read and the field has the annotation @Null a null value will be set. If the field has @NotNull annotation a value will be made.<br> 
*   ALL: Make will set null for all fields. Fields that will be treated with specialized factory will not be interfered for this tag. If the tag JSR303 is defined to read and the field has the annotation @Null a null value will be set. If the field has @NotNull annotation a value will be made.<br>

<b>Test Tag</b><br>
```<XML>
<tests>
    <test>
      <names>
        <name>Test_Name_1</name>
        <name>Test_Name_2</name>
      </names>
      <entities>
        <entity class="br.com.tests.Employee">
          <field name="name">isName</field>
          <field name="age">between{18,69}</field>
        </entity>
        <entity class="br.com.tests.Employer">
          <field name="name">in{CIA X,CIA Z,CIA B}[,]</field>
        </entity>
      </entities>
    </test>
     <test>
      <names>
        <name>Test_Name_3</name>
      </names>
      <entities>
        <entity class="br.com.tests.Employee">
          <field name="salary">between{3000,4500}</field>
        </entity>
      </entities>
    </test>
</tests>
```
At the <tests> tag you can create more the one test and give a name or names to all tests. For this you must use the <name> tag.
These test names can make easy changes in some rules between tests. The tests can keep one file and merge more than one test in one call:
```<java>
 MakeEntity.make(Employee.class, "Test_Name_1","Test_Name_3");
 MakeEntity.make(Employee.class, "Test_Name_2");
```
Make method receive a String var-args as a second argument. When this parameter is informed Make will search all tests that have the name informed. The order is important. The first rule searched is kept. I mean that: a rule from the first test name will no replaced by a second rule for a second name for the same class field.<br>
The next tag is entities. This tag enable to create a lot of rules fields in the same test. You do not need put a tag for each field class. Only the fields that you want to define specialized rules. In this example Employer or Employee can have each one 10 fields but only the fields in XML will have specialized rules for values. Others fields will use JSR303 if have or free values if have not.<br>
Notice that is necessary the class value be informed with the class full name.<br>

<b> Specialized Factories </b><br>
Some fields need to be treated with a special value. The value needs to be more than valid it needs to be a specialized value. Like a contract number, a social number, drive id and so on. For this cases you can use or create a specialized factory.<br>
Each specialized factory has a key to map with a field that you want to make a value. You can do a mapping between a field and a specialized factory in a field tag inside of the entity tag:<br>
```<XML>
        <entity class="br.com.tests.Employee">
          <field name="name">isName</field>
          <field name="age">between{18,69}</field>
        </entity>
```

In this above case the specialized factories that answer for "isName" and "between{?,?}" will make those values.<br>
Notice the class employee can have more fields. All of them will be worked by Make, but only the fields called name and age will have a specialized factory to work on.<br>

The framework Make has some specialized factories implemented that you can use:<br>
*   <b>MakeBetween</b> tag: between{start,end} Examples:  <field name="age">between{18,80}</field> <field name="temperature">between{-90.50,90.50}</field>
     Works with all types of numbers: byte, short, int, long, float and double. Include wrappers. Work with String too, but the values used to start and end must be numeric.

*   <b>MakeIn</b>      tag: in{x,y,z}[,] Examples: <field name="age">in{10;20;30}[;]</field> in{10.30|10.50|10.80}[|] in{A,B,C}   
	Works with numbers and characters. MakeIn will choose a value in the range informed.<br>
	Notice the character between the [?] is the separator between possible values. If is not informed will be used comma.<br> 

*   <b>MakeEmail</b>   tag: isEmail      Example: <field name="email">isEmail</field>
	A String in email format will be created. Works only with String. Before @ a String will be between 3 and 8. After @ all values are chosen in emails_make.properties file.

*   <b>MakeName</b>    tag: isName       Example: <field name="name">isName</field>
	Create a person name.  Works only with String. Two names are used to create the name. All names come from names_make.properties file 
	
*   <b>MakeList</b>    tag:isList{class name}[I,E] Example: <field name="employees">isList{br.com.pro.Employee}[5,25]</field>
	Some relations are one to many and for those case a list can be necessary.<br>
	Inside of { } inform the full class name that will be created to add a List.<br>
	The class referenced between "{" "}" needs to have a default constructor. A constructor without arguments.<br>
	Inside of [ ] inform the minimum and maximum amount of objects that need to be created to add the List.<br> 
	
*   <b>MakeSet</b> tag:isSet{class name}[3,5] Example: <field name="employees">isSet{br.com.pro.Employee}[5,25]</field>
	Some relations are one to many and for those case a set can be necessary.<br>
	Inside of { } inform the full class name that will be created to add a Set.<br>
	The class referenced between "{" "}" needs to have a default constructor. A constructor without arguments.<br>
	Inside of [ ] inform the minimum and maximum amount of objects that need to be created to add the Set.<br>
	A collection with Set cannot have two objects as equals. Make will try to create a Set with a satisfactory amount. But if the framework cannot add objects because of equals the set can have less objects than configured as minimum at isSet value. 

You must create all in the TEST source of your project.
To create a specialized factory two steeps are need:
*   Implement ValueSpecializedFactory:
```<java>
boolean workValue(String fieldName, String value);
void makeValue(Field field, T entity, String... testName);
boolean isWorkWith(Field field, T entity);
```
*   Declare the class in make.xml at tag <factories>: 
```<XML>
  <factories>
    <factory>br.com.gbvbahia.maker.properties.CepWorkTest</factory>
    <factory>br.com.gbvbahia.maker.properties.FactoryCustomerService
    </factory>
  </factories>
```
<br>DO NOT put XML setup file in make folder test. It is must be put in the application folder resource test. 


## Installation
You can download the project and put it as test dependence in your project.

To build it you will need the Apache maven.
