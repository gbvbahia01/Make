# MAKE: UM FRAMEWORK PARA GERAÇÃO DE DADOS PARA TESTES UNITÁRIOS EM JAVA #
O objetivo  framework Make é gerar valores aleatórios, respeitando as validações JSR-303, impostas no sistema, ou seja, gerando sempre valores válidos.
O intuito do framework é reduzir a necessidade de criar valores para realização de testes e dar ferramentas para criar os valores necessários de forma rápida e simples, diminuindo o tempo da preparação do teste.
O Make pode ser utilizado juntamente com o código de testes da aplicação, criando objetos para serem utilizados nos testes unitários atendendo as validações impostas pela JSR-303 ou respeitando os limites impostos pelo desenvolvedor. Também pode ser utilizado para gerar dados válidos, porém irreais, para popular uma base de dados de desenvolvimento. Nesta segunda utilização, recomendamos que seja feito um projeto em paralelo ao projeto aplicado, não utilizando o código de testes para isso, para não poluir o código de testes com outra tarefa totalmente distinta.
O Make possui uma classe principal para a utilização, essa classe é chamada de MakeEntity, para criar uma entidade o desenvolvedor deverá chamar um dos métodos sobrecarregados MakeEntity.makeEntity:
```
•	T makeEntity(Class<T> entity): Recebe a classe a ser criada, irá ignorar relacionamentos com objetos criados pelo desenvolvedor e não irá procurar o arquivo auxiliar make.properties.
•	T makeEntity(Class<T> entity,boolean makeRelationships): O segundo parâmetro, makeRelationships, possibilita ao desenvolvedor solicitar ao Make que crie objetos relacionados anotados com @Entity da JPA. Não irá procurar o arquivo auxiliar make.properties.
•	T makeEntity(String testName,Class<T> entity): Possibilita informar o nome do teste para carregar informações do arquivo make.properties. Irá ignorar relacionamentos com objetos criados pelo desenvolvedor.
•	T makeEntity(String testName, Class<T> entity,boolean makeRelationships): Possibilita informar o nome do teste e solicitar ao Make que crie objetos relacionados anotados com @Entity da JPA.
•	List<T> makeEntities(String testName, Class<T> entity, int amount, boolean makeRelationships): O parâmetro amount possibilita criar uma lista com vários objetos gerados pelo Make, com ou sem relacionamentos e com ou sem definições no arquivo make.properties.
```

Exemplos de utilização:

```
 EntityTest test = MakeEntity.makeEntity(EntityTest.class);
 EntityTest test = MakeEntity.makeEntity("myTest", EntityTest.class);
 EntityTest test = MakeEntity.makeEntity(null, EntityTest.class, false);
 List<EntityTest > tests = MakeEntity.makeEntities(null, EntityTest .class, 10, false);
```