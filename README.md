Tutorial como abrir projeto Spring boot Eclipse

1. Abra o eclipse
2. Va na aba File
3. Procure a opção “import”
4. Pesquise por “maven project”
5. No retorno da pesquise escolha a opção “existing maven project”
6. Clique em “browse” para procurar pela pasta do projeto 
7. Procure pela pasta do projeto “cef” clique em abrir
8. Espere algum tempo até o projeto ser importado
9. Após isso vá ao diretório “src/main/resources/” e abra o arquivo "application.properties", esse arquivo contem as configuração do banco de dados:

  spring.jpa.hibernate.ddl-auto=create << essa opção “create” indica que o programa irá gerar o esquema de tabelas para você com base nos modelos do projeto.

  spring.datasource.url=jdbc:mysql://localhost:3306/cef << essa configuração aponta o caminho do banco de dados, crie uma database no seu banco de a chame de “cef”. 

  spring.datasource.username=root 
  spring.datasource.password=root

  As configuração de senha e usuário cabe ao dono do banco saber quais são.

  Após criar uma uma database chamada ‘cef“, vá ao diretório no projeto “src/main/java/com/mk/cef” e abra a classe CefApplication.java e execute essa classe na opção “Run As” ou “Debug As” JavaApplication. 

  Feito isso abra o seu navegador e abra essa url “http://localhost:8081/cef/app/funcionario/list”

  E pronto.
