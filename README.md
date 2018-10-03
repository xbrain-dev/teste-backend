#### Tarefas:
- Criar uma venda.
- Retornar o ranking dos 10 melhores vendedores da semana com média de vendas diaria.

#### Dependências:
- JPA
- H2
- Web

#### Exemplos:
POST http://localhost:8080/vendedores
```
{
  "nome":"MATHEUS ALCANTARA LOPES",
  "cpf":"07644413375"
}
```
POST http://localhost:8080/vendas
```
{
    "vendedor": {
        "id": 1,
        "nome": "MATHEUS ALCANTARA LOPES",
        "cpf": "07644413375"
    },
    "data": "2018-10-03",
    "valor": 10.50
}
```
GET http://localhost:8080/vendedores/ranking?dataInicial=2018-10-03&dataFinal=2018-10-03
```
[
    {
        "id": 1,
        "nome": "MATHEUS ALCANTARA LOPES",
        "cpf": "07644413375",
        "dataInicial": "2018-10-03",
        "dataFinal": "2018-10-03",
        "quantidadeVendas": 1,
        "valorMedioDiario": 10.50,
        "valorTotalPeriodo": 10.50
    }
]
```
