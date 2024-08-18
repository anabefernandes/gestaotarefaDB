
## Primeiro projeto em Java com JDBC

Esse é o meu primeiro projeto em Java usando o conceito de JDBC e MySQL.
É um gerenciador de tarefas de console, onde é possivel adicionar uma tarefa, editar, excluir, mostrar todas as tarefas ou apenas as pendentes e concluídas.
Feito para a aula de Técnicas de Programação II, do 3º Ciclo de Desenvolvimento de Software Multiplataforma - FATEC, com o professor Alessandro.


### Conectando com o banco de dados:

Para rodar esse projeto, utilizei o IntelliJ, xampp e o JDBC MySQL.

Após instalados, descompactar o JDBC e dar start no xampp em Apache e MySQL, criar o projeto na IDE e para conectar o projeto no data base basta clicar com o botão direito no nome do projeto > "Open Module Settings" (ou F4) > "Libraries" > Clica no + > "Java" > procura a pasta do JDBC ja descompactada e seleciona o arquivo "mysql-connector-j-9.0.0.jar" > salvar.

No localhost criar o "gestaotarefas" e a tabela "tarefas" com id (auto increment), nome, descrição e status.


