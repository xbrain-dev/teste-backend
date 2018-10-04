INSERT INTO vendedor(id, nome, cpf) VALUES (1, 'JODAYLANE RIBEIRO DOS SANTOS', '61509669337');
INSERT INTO vendedor(id, nome, cpf) VALUES (2, 'JOAO APARECIDO', '06909550307');
INSERT INTO vendedor(id, nome, cpf) VALUES (3, 'ALFREDO JONES', '06607371329');
INSERT INTO vendedor(id, nome, cpf) VALUES (4, 'PEDRO DA SILVA', '61532198370');
INSERT INTO vendedor(id, nome, cpf) VALUES (5, 'LUCIA FIGUEIREDO', '03990619322');

INSERT INTO venda(id, data, valor, vendedor_id) VALUES (1, '2018-10-03', 10.00, 1);
INSERT INTO venda(id, data, valor, vendedor_id) VALUES (2, '2018-10-03', 7.50, 1);
INSERT INTO venda(id, data, valor, vendedor_id) VALUES (3, '2018-10-03', 20.00, 1);

INSERT INTO venda(id, data, valor, vendedor_id) VALUES (4, '2018-10-03', 15.00, 2);
INSERT INTO venda(id, data, valor, vendedor_id) VALUES (5, '2018-10-03', 30.00, 2);

INSERT INTO venda(id, data, valor, vendedor_id) VALUES (6, '2018-10-03', 43.00, 3);

INSERT INTO venda(id, data, valor, vendedor_id) VALUES (7, '2018-10-03', 2.00, 4);
INSERT INTO venda(id, data, valor, vendedor_id) VALUES (8, '2018-10-03', 10.00, 4);

INSERT INTO venda(id, data, valor, vendedor_id) VALUES (9, '2018-10-03', 6.00, 5);