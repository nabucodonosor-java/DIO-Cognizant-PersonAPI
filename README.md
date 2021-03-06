# Bootcamp Java Developer Cognizant - Digital Innovation One
## PersonAPI
API desenvolvida durante bootcamp da DIO com o objetivo de criar um CRUD de pessoas. Foi utilizado a linguagem Java na versão 11 e o Spring Framework.
### Testes Automatizados
São testes para confirmar se o sistema está de acordo com os requisitos exigidos e suas respectivas regras de negócio. O desenvolvimento dos testes respeitam o padrão AAA (Arrange: instancie os objetos necessários, Act: execute as ações necessárias e Assert: declare o que deve acontecer) e são divididos em:
* Teste de Unidade: teste de apenas uma unidade sem carregar o contexto da aplicação (entidade, repositório, serviço e controlador)
* Teste de Integração: teste que carrega o contexto da aplicação e envolve mais de uma unidade do sistema.
#### Anotações utilizadas nas classes de teste
* @SpringBootTest: carrega o contexto da aplicação (teste de integração);
* @AutoCOnfigureMockMvc: carrega o contexto da aplicação e trata as requisições sem subir o servidor (teste de integração e web);
* @WebMvcTest: carrega o contexto porém somente da camada web (teste de unidade do controlador rest);
* @ExtendWith(SpringExtension): não carrega o contexto mas permite usar os recursos do Spring com JUnit (teste de unidade nas camadas de servicos e componentes;
* @DataJpaTest: carrega somente os componentes relacionados ao Spring Data JPA. Cada teste é transacional e dá rollback ao final (teste de unidade em repositórios.
#### Fixtures do JUnit5
* @Test: anotação utilizada nos métodos para sinalizar que é um método de teste;
* @BeforeAll: realiza a preparação dos objetos antes de todos testes da classe;
* @AfterAll: realiza a preparação dos objetos depois de todos testes da classe;
* @BeforeEach: realiza a preparação dos objetos antes de cada teste da classe;
* @AfterEach: realiza a preparação dos objetos depois de cada teste da classe;
#### Anotações Mockito
* @Mock: utilizar quando a classe de teste não carrega o contexto da aplicação. Mais ráoido e enxuto;
* @MockBean: utilizar quando a classe de teste carrega o contexto da aplicação e precisa mockar algum bean.

