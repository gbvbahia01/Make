# Make V.2.0 
A framework that can make easily a lot of entity or objects to use in unit tests or populate a data base.

## Motivation
1.   I want to use the JSR303 specification in my entities without a hell in my JUnit tests.<br>
2.   Create a database that can be reliable to do tests.<br>


## How to

```<java>
MyObject myObject = MakeEntity.make(MyObject.class);
List<MyObject> myObjects = MakeEntity.makes(MyObject.class, amount);
```

## Mechanics

<b>For a object be made by Make it only need to have a default constructor. A constructor without arguments.</b><br>

Objects are defined with fields. Usually is necessary to define some rules in those fields. Like a car cannot have -3.5 of gasoline and cannot pass tha maximum amount in its fuel tank.<br>
Make can create those values respecting all rules.<br>
If you use JSR303 annotations Make will read them and use to create the fields values. Make will do a reverse engineering to create valid values for each field. With the only exception will be the annotation @Pattern. For this annotation you will have to create a value. A good thing is that  Make has a lot of tools to help you.<br>
All values created using the JSR303 will be valid, but some of them cannot be acceptable. Like a field that represents a birth date for a person. You can use the annotation @NotNull with @Past. In this case the values will be in the past therefore be valid. Notice that Make can create dates like one day ago or 200 years ago and for the business will be more satisfactory some dates between 18 and 80 years. For situations like that Make has space to put a engine to create those values. This engine is called Specialized Factories.<br>
If you do not use JSR303 annotations Make will create open values for all fields. You can change this behavior using the Specialized Factories too.

## Requirements
To build it you will need the Apache Maven.<br>
For Eclipse IDE you may need <a href="http://www.eclipse.org/m2e/" target="_blank">M2Eclipse</a> to manage Make project.<br>

## Installation
You can download the project.<br>
Download the project.<br>
<b>Eclipse IDE </b><br>
After download the project and import it in Eclipse right click on project in Maven choose Update project...<br>
If Eclipse complain about compilation problems right click on project and choose Properties, Java Compiler and uncheck "Use compliance from execution environment..." and in "Compiler compliance level:" choose 1.6. Apply and OK.<br>
Right click on project again and choose Run As, choose the first Maven build. If Eclipse ask about environment check if in Base directory is the path for the Make folder and in Goals write: package. At least choose run button.<br>
<b>NetBeans IDE</b><br>
I believe that in NetBeans any problem will happen. You only need import the project and build.

After you finish the IDE steps you need to put Make as Maven lib in your m2 folder. You must go in the Make folder and run the command: mvn install.<br>
Copy from folder src/test/resource the make.xml file and paste it into your project resource test folder.<br>
A project as example can be downloaded here: <a href="https://github.com/gbvbahia01/Making/tree/master" target="_blank">Making</a>
Follow the next step to configure this file.<br>

## Setup File
The configuration of the framework is made with a XML setup file. Is very important that you put this file in your project test resource folder.<br>
Make uses the name make.xml for XML setup file by default but you can change it. If you do, before start to create objects with Make you need to call a static method at Factory class called loadSetup as the name of XML setup file as parameter:<br>

```<java>
 Factory.loadSetup("myXMLSetup.xml");
```

You can get a XML setup file example in src/test/resource folder from Make project.<br>
From now I will call it make.xml.<br>
Let's take a look in the make.xml file.<br>
The make.xml is divided in 3 parts:<br>

<b>Setup Tag </b><br>
```<XML>
  <setup>
    <JSR303 value="read" /><!-- read or ignore -->
    <Null value="never" /><!-- never some all -->
  </setup>
```
The JSR303 tag is used to setup the behavior about JSR303 annotations. To read or to ignore those annotations. If your project does not have these annotations, you must set ignore here.<br>
*   READ: Make will be looking for JSR303 annotations to create valid values.
*   IGNORE: Make wont be looking for JSR303 and the values will be open to any value that the field can handle.<br>

The Null tag determines how the framework will work about to set null values:<br>
*   NEVER: Make always will create values for all fields. Three cases can set null at a field:<br> 
      1 - the field type does not have default constructor.<br>
      2 - JSR303 is defined to read and the field has the annotation @Null.<br>
      3 - Fields that will be treated with specialized factory.<br>
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
At the <i>tests</i> tag you can create more the one test and give a name or names to all tests. For this you must use the <i>name</i> tag.
These test names can make easy changes in some rules between tests. The tests can keep one file and merge more than one test in one call:
```<java>
 MakeEntity.make(Employee.class, "Test_Name_1","Test_Name_3");
 MakeEntity.make(Employee.class, "Test_Name_2");
```
Make method receive a String var-args as a second argument. When this parameter is informed Make will search all tests that have the name informed. The order is important. The first rule searched is kept. I mean that: a rule from the first test name will not be replaced by a second rule for a second name for the same class field.<br>
The next tag is <i>entities</i>. This tag enable to create a lot of rules fields in the same test. You do not need put a tag for each field class. Only the fields that you want to define specialized rules. In this example Employer or Employee can have each one 10 fields but only the fields in XML will have specialized rules for values. Others fields will use JSR303 if have or free values if have not.<br>
Notice that is necessary the class value be informed with the class full name.<br>

```<XML>
        <entity class="br.com.tests.Employee">
```

## Specialized Factories

Some fields need to be treated with a special value. The value needs to be more than valid it needs to be a specialized value. Like a contract number, a social number, drive id and so on. For this cases you can use or create a specialized factory.<br>
Each specialized factory has a key to map with a field that you want to make a value. You can do a mapping between a field and a specialized factory in a <i>field</i> tag inside of the <i>entity</i> tag:<br>
```<XML>
        <entity class="br.com.tests.Employee">
          <field name="name">isName</field>
          <field name="age">between{18,69}</field>
        </entity>
```

In this above case the specialized factories that answer for "isName" and "between{?,?}" will make those values.<br>
Notice the class employee can have more fields. All of them will be worked by Make, but only the fields called name and age will have a specialized factory to work on.<br>

The framework Make has some specialized factories implemented that you can use:<br>
*   <b>MakeBetween</b> tag: between{start,end} Examples:  &lt;field name=&quot;age&quot;&gt;between{18,80}&lt;/field&gt; &lt;field name=&quot;temperature&quot;&gt;between{-90.50,90.50}&lt;/field&gt;<br>
     Works with all types of numbers: byte, short, int, long, float and double. Include wrappers. Work with String too, but the values used to start and end must be numeric.

*   <b>MakeIn</b>      tag: in{x,y,z}[,] Examples: &lt;field name=&quot;age&quot;&gt;in{10;20;30}[;]&lt;/field&gt; &lt;field name=&quot;xxx&quot;&gt;in{10.30|10.50|10.80}[|]&lt;/field&gt;&lt;field name=&quot;zzz&quot;&gt; in{A,B,C}&lt;/field&gt;<br>
	Works with numbers and characters. MakeIn will choose a value in the range informed.<br>
	Notice the character between the [?] is the separator between possible values. If is not informed will be used comma.<br> 

*   <b>MakeEmail</b>   tag: isEmail      Example: &lt;field name=&quot;email&quot;&gt;isEmail&lt;/field&gt;<br>
	A String in email format will be created. Works only with String. Before @ a String will be between 3 and 8. After @ all values are chosen in emails_make.properties file.

*   <b>MakeName</b>    tag: isName       Example: &lt;field name=&quot;name&quot;&gt;isName&lt;/field&gt;<br>
	Create a person name.  Works only with String. Two names are used to create the name. All names come from names_make.properties file 
	
*   <b>MakeList</b>    tag: isList{class name}[I,E] Example: &lt;field name=&quot;employees&quot;&gt;isList{br.com.pro.Employee}[5,25]&lt;/field&gt;<br>
	Some relations are one to many and for those case a list can be necessary.<br>
	Inside of { } inform the full class name that will be created to add a List.<br>
	The class referenced between "{" "}" needs to have a default constructor. A constructor without arguments.<br>
	Inside of [ ] inform the minimum and maximum amount of objects that need to be created to add the List.<br> 
	
*   <b>MakeSet</b>     tag: isSet{class name}[3,5] Example: &lt;field name=&quot;employees&quot;&gt;isSet{br.com.pro.Employee}[5,25]&lt;/field&gt;<br>
	Some relations are one to many and for those case a set can be necessary.<br>
	Inside of { } inform the full class name that will be created to add a Set.<br>
	The class referenced between "{" "}" needs to have a default constructor. A constructor without arguments.<br>
	Inside of [ ] inform the minimum and maximum amount of objects that need to be created to add the Set.<br>
	A collection with Set cannot have two objects as equals. Make will try to create a Set with a satisfactory amount. But if the framework cannot add objects because of equals the set can have less objects than configured as minimum at isSet value. 

*   <b>MakeCpf</b>     tag: isCPF  Example: &lt;field name=&quot;cpf&quot;&gt;isCPF&lt;/field&gt;<br>
	This specialized factory work with a type of number that exist in Brazil.<br>
	Works only with String.<br>
	
*   <b>MakeCnpj</b>    tag: isCNPJ Example: &lt;field name=&quot;cnpj&quot;&gt;isCNPJ&lt;/field&gt;<br>
	This specialized factory work with a type of number that exist in Brazil.<br>
	Works only with String.<br>

Certainly you will need to create your specialized factory. In this case is quite simple to do this in Make.<br>
You must create all in the TEST source of your project. So choose a package in src/test/java or other source folder test that your project has.<br>
To create a specialized factory two steps are needed:<br>
1º   Implement ValueSpecializedFactory:
```<java>
boolean workValue(String fieldName, String value);
boolean isWorkWith(Field field, T entity);
void makeValue(Field field, T entity, String... testName);
void updateStage(Notification notification);
```
*   boolean workValue(String fieldName, String value) method: this method receives the full name of the field and the value is the key that was put in make.xml for that field. Basically what you have to do is ignore the field name and check if the value is a key expected for this specialized factory class that you are making.<br>
Let's take a look at the workValue in MakeName class:

```<java>
  public boolean workValue(String fieldName, String value) {
    if (KEY_PROPERTY.equals(StringUtils.trim(value))) {
      return true;
    }
    return false;
  }
```
KEY_PROPERTY in this case is "isName". So if value is equal isName this class will inform that can make the value for this field.<br>

*   boolean isWorkWith(Field field, T entity); this method receives the field that will receive a value and the instance of the object that has the field. This method enables that specialized factories check if the type of field is expected for it. DO NOT set any value in this method. Only do checks.<br>
Let's take a look at the workValue in MakeName class:

```<java>
  public <T> boolean isWorkWith(final Field field, final T entity) {
    return field.getType().equals(String.class);
  }
```
It is only check if the field class (field.getType()) is a String class and return true if is.<br>

*  void makeValue(Field field, T entity, String... testName); this method receives the field, the instance of object that has the field and the names of the tests passed as argument in MakeEntity.make method. Again will be quite simple. You will only set the value in a field.<br> 
Let's take a look at the workValue in MakeName class:

```<java>
  public <T> void makeValue(Field field, final T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    field.set(entity, getName());
  }
```
You <b>do not need to check the test names</b>. Make already did for you.   

*   void updateStage(Notification notification); this method is for inform the specialized factories about the stage of the object creation. Usually you will do nothing here. You can leave this method without any implementation if you want to.
When a object is being created by Make if it has a field that is another complex object Make will call MakeEntity.make again doing a recursion. While a object has another object Make will call MakeEntity.make. If you want to know how many and when this happening the method updateStage is the place.<br>
The updateStage receives a Notification object that contains some informations about the object creation stage:<br>
   *   The name or names used to call MakeEntity.make is in Notification.<br>
   *   The stage of the object creation can be known using the methods: isCreationStarted, isCreationFinished, isCreationRecursionBegin and isCreationRecursionEnd.
   *   To know the amount of recursion made by Make you can call the method getRecursion. If the value is 1 means that two calls were made: the first call by developer and the second call made by framework to create a new complex object. If is 2 means that three calls were made: the first by developer and 2 more by the framework. And so on.<br>

The object creation can have 4 stages:<br>
1   BEGIN: When the MakeEntity.make is called by developer.<br> 
2   END: When the MakeEntity.make called by developer finished the object creation.<br>
3   RECURSION_BEGIN: When Make needs to call itself again, calling MakeEntity.make.<br>
4   RECURSION_END: When a called made by Make to itself is finished. <br>
Once again: <b> You can leave  void <i>updateStage(Notification notification);</i> without any implementation.</b> Only for specific situations you will need it.<br>

2º   Declare the class in make.xml at tag <factories>: 
```<XML>
  <factories>
    <factory>br.com.mypackage.CepWorkTest</factory>
    <factory>br.com.mypackage.FactoryCustomerService
    </factory>
  </factories>
```
Your specialized factory is ready for be used now. To work is only need to map a field class to it and Make will do the rest for you. 

And remember:
<br>DO NOT put XML setup file in make folder test. It is must be put in the application folder resource test.

## API Reference
To create those values a large static API were made. With a lot of static methods that can be used as a Swiss Army Knife into JUnit tests. After add Make as a test dependence in our project you can use all these methods listed below:

*   Boolean    MakeBoolean.getBoolean();                    Can return true or false.<br>
*   Character  MakeCharacter.getNumber();                   A character between 0 and 9.<br>
*   Character  MakeCharacter.getLetter();                   A character between a and Z.<br>
*   Character  MakeCharacter.getSymbols();                  A character in !, @, #, $, &, %, ?, -, +<br>
*   Character  MakeCharacter.getCharacter();                Any character.<br>
*   Byte       MakeByte.getMax(byte max);                   A random byte limited by max value.<br>
*   Byte       MakeByte.getRange(byte min, byte max);       A random number between min and max parameters.<br>
*   Short      MakeShort.getMax(short max);                 A random short limited by max value.<br>
*   Short      MakeShort.getRange(short min, short max);    A random number between min and max parameters.<br>
*   Integer    MakeInteger.getMax(int max);                 A random integer limited by max value.<br>
*   Integer    MakeInteger.getRange(int min, int max);      A random number between min and max parameters.<br>
*   Long       MakeLong.getMax(long max);                   A random long limited by max value.<br>
*   Long       MakeLong.getRange(long min, long max);       A random number between min and max parameters.<br>
*   Float      MakeFloat.getMax(float max);                 A random float limited by max value.<br>
*   Float      MakeFloat.getRange(float min, float max)     A random number between min and max parameters.<br>
*   Double     MakeDouble.getMax(double max)                A random double limited by max value.<br>
*   Double     MakeDouble.getRange(double min, double max)  A random number between min and max parameters.<br>
*   BigInteger MakeBigInteger.getMax(long max);             A random BigInteger limited by max value.<br>
*   BigInteger MakeBigInteger.getRange(long min, long max)  A random number between min and max parameters.<br>
*   BigDecimal MakeBigDecimal.getMax(double max);           A random BigDecimal limited by max value.<br>
*   BigDecimal MakeBigDecimal.getRange(double i, double e); A random number between min and max parameters.<br>
*   Calendar   MakeCalendar.getInFuture();					A Calendar in the future limited to 1800 days forward.<br>
*   Calendar   MakeCalendar.getInPast();                    A Calendar in the past limited to 1800 days behind.<br>
*   Calendar   MakeCalendar.getCalendar();                  A Calendar in the past or in the future with 1800 days limited.<br>
*   Date       MakeDate.getInFuture();					    A Date in the future limited to 1800 days forward.<br>
*   Date       MakeDate.getInPast();                        A Date in the past limited to 1800 days behind.<br>
*   Date       MakeDate.getCalendar();                      A Date in the past or in the future with 1800 days limited.<br>
*   String	   MakeString.getString(int min, int max, StringType type); It makes a String with amount of characters between min and max parameters. Use type parameter to control the types of character inside of the String.<br>
*   String     MakeString.getString(int characters, StringType type);    It makes a String with a size informed in parameter characters. Use type parameter to control the types of character inside of the String.<br>
*   String     MakeString.getLoren(int characters);                      It makes a String using the loren_make.properties file.


## Contributors
Guilherme Braga <a href="https://br.linkedin.com/in/guilherme-braga-a7994823" target="_blank">(LinkedIn)</a>

## License
<a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache 2.0</a>
