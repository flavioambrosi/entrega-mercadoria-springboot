Problema proposto:

Desenvolver o novo sistema de entregas visando sempre o menor custo. Para popular sua base de dados o sistema precisa expor um Webservices que aceite o formato de malha logística (exemplo abaixo), nesta mesma requisição o requisitante deverá informar um nome para este mapa. É importante que os mapas sejam persistidos para evitar que a cada novo deploy todas as informações desapareçam. O formato de malha logística é bastante simples, cada linha mostra uma rota: ponto de origem, ponto de destino e distância entre os pontos em quilômetros.

AB 10
BD 15
AC 20
CD 30
BE 50
DE 30

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e seu caminho, para isso ele passará o nome do ponto de origem, nome do ponto de destino, autonomia do caminhão (km/l) e o valor do litro do combustível, agora sua tarefa é criar este Webservices. Um exemplo de entrada seria, origem A, destino D, autonomia 10, valor do litro 2,50; a resposta seria a rota A B D com custo de 6,25.

De inicio identifiquei que se trata de um problema de menor caminho e para soluciona-lo estou utilizando a API JUNG (http://jung.sourceforge.net/). Essa api é responsável pela criação do grafo e do algoritmo Dijkstra. Implementadas as classes Aresta e Vertice que serão persistidas e utilizadas no grafo. 

Foram criados serviços Restfull para a criação de rotas(/servicos/adicionaMapa/{origem}/{destino}/{distancia} e busca de menor caminho (/servicos/buscaCaminho/{origem}/{destino}/{autonomia}/{valorCombustivel}

O projeto foi desenvolvido utilizando Spring Boot e Spring Data utilizando Hibernate como framework de persistencia.

Foi utilizado banco Oracle e segue o arquivo para criação do banco.

Seguir os passos abaixo para executar o projeto
- Baixar o zip do repositorio
- Alterar o arquivo src\main\resources\application.properties e alterar as informações do banco de dados
- mvn package
- java -jar target/entrega-mercadoria-0.0.1-SNAPSHOT.jar

Para cadastrar rotas deve-se chamar a URL http://localhost:8090/servicos/adicionaMapa/{origem}/{destino}/{distancia}  
Para busca de menor caminho cahamr a url  http://localhost:8090//servicos/buscaCaminho/{origem}/{destino}/{autonomia}/{valorCombustivel}

Deve-se informar os parametros entre {}.
