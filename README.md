# Controle remoto de estoque com RMI
Exemplo de uso de Remote Method Invocation do Java para Apresentação em disciplina de Desenvolvimento de Sistemas Distribuídos e Parelelos

_André Luiz Cordeiro Gomes <https://github.com/CordeiroAndre>_

_Thiago Farias <https://github.com/thiagofarias0907>_

### Conceito
Um sistema distribuído de controle de estoque, onde os produtos existentes manipulados são exclusivamente pela aplicação instalada no server, capaz de operações de persistência. 
Um servidor com um sistema (Server) onde somente nele há manipulação de estoque de produtos (crud e controle de quantidade). Diversas máquinas simples em uma loja nos guichês de atendimento a cliente
seriam teria um sistema simples "Clients" que só pode operar sobre o estoque efetuando as saídas de produto, pois não foram pensados para controlar todas as regras de um controle de estoque; Há uma comunicação intranet entre as máquinas na loja e o servidor do estoque.

#### Característica esperada do sistema distribuído
- **Quantidade de servers**: 1
- **Quantidade de clientes**: demanda variável de 1 a 20
- **Tipo de Comunicação**: RMI
- **Linguagem**: Java
- **"Troca de mensagens"**: “exportação” de objetos entre as classes, operando os métodos de objetos implementados no server como: identificação dos produtos no registry, leitura da quantidade em estoque e venda / baixa de produtos
- **Padrões de projeto**: MVC, Strategy

**Configure o host e porta nas aplicações no início**
---

## Server

### Requisitos Funcionais
- **RF01** Sistema deve permitir cadastrar produtos;
- **RF02** Sistema deve permitir alterar produtos;
- **RF03** Sistema deve permitir alterar a quantidade de itens de um produto em estoque;
- **RF04** Sistema deve permitir visualizar lista de produtos cadastrados no estoque;

### Regras de Negócio
- **RN01** Produtos devem ter identificador único;
- **RN02** Métodos de leitura de dados sobre os produtos devem ser disponíveis para acesso remoto pelo Client;
- **RN03** Método de baixa / venda de produto deve ser disponível para acesso remoto pelo Client;
- **RN04** Apenas produtos com itens em estoque podem ser vendidos;

### Requisitos Não Funcionais
- **RNF01** Sistema deve usar padrão RMI para que de aplicação Client manipule produtos do estoque;
- **RNF02** Sistema deve ser feito em Java;
- **RNF03** Sistema deve lidar com diversos clients ao mesmo tempo tentando manipular os objetos do estoque.

---

## Client

### Requisitos Funcionais
- **RF01** Sistema deve permitir visualizar lista de produtos cadastrados no estoque;
- **RF02** Sistema deve permitir ler dados dos produtos;
- **RF02** Sistema deve permitir filtrar produtos da lista;
- **RF03** Sistema deve permitir efetuar a venda de produto em estoque;
- **RF03** Sistema deve permitir configurar o endereço para comunicação com a aplicação server;

### Regras de Negócio
- **RN01** Produtos devem ter identificador único;
- **RN02** Métodos de leitura de dados sobre os produtos serão por acesso remoto ao Server;
- **RN03** Método de baixa / venda de produto deve ser por acesso remoto a objeto Produto no Server;
- **RN04** Apenas produtos com itens em estoque podem ser vendidos;

### Requisitos Não Funcionais
- **RNF01** Sistema deve usar padrão RMI para que de aplicação Client manipule produtos do estoque na aplicação Server;
- **RNF02** Sistema deve ser feito em Java;
- **RNF03** Sistema deve lidar com diversos clients ao mesmo tempo tentando manipular os objetos do estoque.



---

## Docs
Acesse na pasta _./docs_ os arquivos e diagramas do projeto:
  - *Pacotes Jar*: client.jar (Cliente) e server.jar (Server)
  - *diagrama_classes*: principais classes relacionadas à implementação do RMI
  - *diagrama_sequencia*: sequencia de ações durante a execução do sistema distribuído
