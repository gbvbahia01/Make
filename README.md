# Make V.2.0
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
For me as a developer I chose the second. I was happy with the validation in JPA2 and JSF2 forms and a lot of bugs avoided with JSR303.<br>
For my boss, as a manager, time is money and spend a lot of time in test code is not acceptable.<br>
When my coworkers realized the big job to refactor all those code. They went to manager side. I was alone :(<br>
So I had the idea to make this framework and we decided to remove the JUnit tests until it get ready.<br>
Yes is true. I started to work with it in my house at nights. Worth it, do not worry. I got a university specialization using it;).<br>
When I put it into our application I realized that also I could used to populate my test data base. I could see a data base with 100.000 objects created in 3 hours. You must realize that I am inlcuding the realation between objects is also included in this amount. Our 50 tables were crowded with a lot of valid data.<br>
Now is in second version available for all because I worked in my house, not in my job. ;)

## Requirements
The object to be made needs to have a default constructor. A constructor without arguments.<br>
Make will create all fields for the object, if the field is another complex object is necessary that object has a default constructor too or null will be set.

## Setup
We define or objects with fields. Sometime we need to define rules about the values. Like a car cannot have -3.5 of gasoline and cannot pass tha maximum amount of the fuel tank.<br>
Make will create the values respecting those rules.<br>
If you use JSR303 annotations Make will read them and use to create the fields values. With the only exception about the annotation javax.validation.constraints.Pattern. For this annotation you will have to create a value. You can use another Make tool.<br>
If you do not use JSR303 annotations Make will create open values for all fields but you can change this behavior using the XML file setup. In this file you can define a range of values or other rule for a lot of fields.<br>
The configuration of the framework is made with a XML setup file. Is important that this file have to be put in your resource folder test.<br>
You can get a exemple XML setup file in src/test/resource folder from this project. Check the file make.xml.<br>
Make use this name by default but you can change it.<br>
The XML setup file is divided in 3 parts:<br>
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
*   
<b> Specialized Factories <b />
Some fields need to be treated with a special value. Like a contract number, a social number, drive id and so on. For this cases you can create a specialized factory. Create in the TEST source of course.
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
