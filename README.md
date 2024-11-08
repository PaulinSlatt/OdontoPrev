# Nome da aplicação: Sistema de Recomendação para Tratamentos Preventivos
 
## Integrantes do Grupo

*Paulo Sergio Caetano Moreno Mafra* - Estudante na unidade Aclimação, responsável pelo código fonte.
*Giovanna Lima Giantomaso* - Estudante na unidade Paulista, responsável pelo banco de dados.    
*Rebeca Silva Lopes* - Estudante na unidade Paulista, responsável pela documentação do projeto.
 
## Requisitos do Software
 
- Instalação do Java 17;
- Instalação do MySQL na máquina.
 
## Instruções de como rodar a aplicação
 
1. Clonar o projeto;
2. Baixar as dependências do Groovy;
3. Rodar os scripts SQL;
4. Rodar o projeto;
5. Executar as requisições.
 
## Link da Proposta Tecnológica
 
[Proposta Tecnológica](https://youtu.be/O7DQT36o9Ck?si=cFJRVk0jR93B9SF7)
 
## Link para Documentação Swagger
 
A documentação Swagger da API está disponível em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
 
## Listagem de Endpoints
 
### Recursos disponíveis
 
- */pacientes*: Representa os pacientes cadastrados no sistema.
- */tratamentos*: Representa os tratamentos disponíveis e associados aos pacientes.
- */sinistros*: Refere-se às ocorrências geradas por tratamentos, como reembolsos ou problemas.
- */recomendacoes*: Fornece recomendações de tratamentos futuros com base nos históricos dos pacientes.
- */historico_tratamento*: Armazena o histórico dos tratamentos realizados por cada paciente.
 
## Uso de Métodos HTTP
 
### Pacientes
 
- GET /pacientes: Retorna a lista de todos os pacientes cadastrados.
- GET /pacientes/{id}: Retorna os detalhes de um paciente específico, baseado no ID.
- POST /pacientes: Cria um novo registro de paciente.
- PUT /pacientes/{id}: Atualiza os dados de um paciente existente.
- DELETE /pacientes/{id}: Remove um paciente do sistema.
 
### Tratamentos
 
- GET /tratamentos: Lista todos os tratamentos disponíveis.
- GET /tratamentos/{id}: Retorna os detalhes de um tratamento específico.
- POST /tratamentos: Adiciona um novo tratamento.
- PUT /tratamentos/{id}: Atualiza um tratamento existente.
- DELETE /tratamentos/{id}: Remove um tratamento.
 
### Sinistros
 
- GET /sinistros: Retorna a lista de sinistros registrados.
- GET /sinistros/{id}: Exibe detalhes de um sinistro específico.
- POST /sinistros: Cria um novo registro de sinistro.
- PUT /sinistros/{id}: Atualiza um sinistro existente.
- DELETE /sinistros/{id}: Exclui um sinistro.
 
### Recomendações
 
- GET /recomendacoes: Retorna as recomendações de tratamentos para os pacientes.
- GET /recomendacoes/{id}: Exibe detalhes de uma recomendação específica.
- POST /recomendacoes: Cria uma nova recomendação para um paciente.
- PUT /recomendacoes/{id}: Atualiza uma recomendação existente.
- DELETE /recomendacoes/{id}: Remove uma recomendação.
 
### Histórico de Tratamentos
 
- GET /historico_tratamento: Lista todos os tratamentos passados dos pacientes.
- GET /historico_tratamento/{id}: Detalha o histórico de tratamento de um paciente específico.
- POST /historico_tratamento: Registra um novo histórico de tratamento.
- PUT /historico_tratamento/{id}: Atualiza o histórico de tratamento de um paciente.
- DELETE /historico_tratamento/{id}: Remove um registro de histórico de tratamento.
