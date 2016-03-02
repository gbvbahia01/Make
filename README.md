# Make V.2.0 
<b>-- I'm doing this documentation please be patient. It is a bit large. --</b><br>
A framework that can make easily a lot of entity or objects to use in unit tests or populate a data base.

## Code Example

```<java>
MyObject myObject = MakeEntity.make(MyObject .class);
List<MyObject> myObjects = MakeEntity.makeEntities(MyObject .class, amount);
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
When I put it into our application I realized that also I could used to populate my test data base. I could see a data base with 100.000 objects created in 3 hours. You must realize that I am inlcuding the realation between objects is also included in this amount. Our 50 tables were crowded with a lot of valid data.<br>
Now is in second version available for all because I worked in my house, not in my job. ;)

## Requirements
The object to be made needs to have a default constructor. A constructor without arguments.<br>
Make will create all fields for the object, if the field is another complex object is necessary that object has a default constructor too or null will be set.

## Mechanics
Objects are defined with fields. Usually is necessary to define some rules in the fields. Like a car cannot have -3.5 of gasoline and cannot pass tha maximum amount in its fuel tank.<br>
Make can create those values respecting all rules.<br>
If you use JSR303 annotations Make will read them and use to create the fields values. Make will read the JSR303 annotations using to do a reverse engineering to create valid values for each field. With the only exception about the annotation @Pattern. For this annotation you will have to create a value. A good thing is that  Make has a lot of tools to help you.<br>
All values created using the JSR303 will be valid, but some of them cannot be acceptable. Like a field that represents a birthdate for a person. You can use the annotation @NotNull with @Past. In this case the values will be in the past therefore be valid. But Make can create dates like one day ago or 200 years ago and for the business will be more satisfactory some dates between 18 and 80 years. For situations like that Make has space to put a engine to create those values. This engine is called Specialized Factories.
If you do not use JSR303 annotations Make will create open values for all fields. You can change this behavior using the Specialized Factories too.

## Setup
The configuration of the framework is made with a XML setup file. Is very important that you put this file in your project test resource folder.<br>
Make uses this name for XML setup file by default but you can change it. If you do, before start to create objects with Make you need to call a static method at Factory class called loadSetup as the name of XML setup file as parameter:<br>

```<java>
 Factory.loadSetup("myXMLSetup.xml");
```

You can get a XML setup file example in src/test/resource folder from Make project.<br>
Let's take a look in the make.xml file.<br>
The XML setup file is divided in 3 parts:<br>
<b>Setup Tag </b><br>
```<XML>
  <setup>
    <JSR303 value="read" /><!-- read or ignore -->
    <Null value="never" /><!-- never some all -->
  </setup>
```
The JSR303 tag is used to inform the framework to read or to ignore the annotations JSR303. If you project does'nt have this annotations you must have to set ignore here.<br>
The Null tag determines how the framework will work about when set null values:<br>
*   The 'never' value tells to the Make to create values for all fields. Only fields that have no default constructor will be set null or fields that will be treated with specialized factory (this last I will explain soon). If the tag JSR303 is defined to read and the field has the annotation @Null a null value will be set.<br>
*   The 'some' value will define If a value will be made or null will be set. One chance to six to be a null value. Fields that will be treated with specialized factory will not be interfered for this tag. If the tag JSR303 is defined to read and the field has the annotation @Null a null value will be set. If the field has @NotNull annotation a value will be made.<br> 
*   The 'all' value means that Make will set null for all fields. Fields that will be treated with specialized factory will not be interfered for this tag. If the tag JSR303 is defined to read and the field has the annotation @Null a null value will be set. If the field has @NotNull annotation a value will be made.<br>

<b>Test Tag</b><br>
````<XML>
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
In the tests tag you can create more the one test and give a name or names to all tests. For this you must use the <name> tag.
This names make easy change rules between tests keeping one file and merge more the one test in one call:
```<java>
 MakeEntity.make(Employee.class, "Test_Name_1","Test_Name_3");
 MakeEntity.make(Employee.class, "Test_Name_2");
```
Make method receive a String var-args as a second argument. When is informed Make will search all tests that have the name informed. The order is important. The first rule is kept. I mean that: a rule from the first test name will no replaced by a second rule for a second name for the same class field.<br>
The next tag is entities. This tag enable to create a lot of rules fields in the same test. You do not need put a tag for each field class. Only the fields that you want to define rules. In this example Employer or Employee can have each one 10 fields but only the fields in XML will have rules for values. Others fields will use JSR303 if have or free values if have not.<br>
It is necessary on entity tag the class value you must inform the class full name.<br>

<b> Specialized Factories </b><br>
Some fields need to be treated with a special value. Like a contract number, a social number, drive id and so on. For this cases you can create a specialized factory. You must create all of it in the TEST source of course.
A specialized factory needs to implement ValueSpecializedFactory:
```<java>
boolean workValue(String fieldName, String value);
void makeValue(Field field, T entity, String... testName);
boolean isWorkWith(Field field, T entity);
```




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
