<h3 align="center">
  Spring x JUnit4 x Mockito x Jacoco !
</h3>

</br>
</br>

**Sommaire :**

> [Introduction](#--introduction)
> 
> [Test unitaire classique](#--cas-classique-)
>
> [Test des exceptions](#--facultatif-test-de-la-levée-des-exceptions-)
>
___

<h4 align="center">
  INTRODUCTION
</h4>

Les tests unitaires permettent de valider de manière atomique les comportements métier.
Dans le cas du développement web il arrive très souvent que des pièces du puzzle soit 
bloquantes pour les tests, par exemple une base de donnée qui n'est pas encore disponible 
ou bien pas encore alimentée, ou encore un service distant qui doit nous retourner des 
données sur les plats que l'on va se faire un plaisir de manger, mais cette base de 
donnée nous répond bien trop lentement pour X raison. Dans ces cas-ci notamment, il 
convient de 'mocker' ces ressources, afin de ne plus dépendre de leur implémentation ou 
de leur créneaux de disponibilitée afin de tester ce que nous nous développons. On va 
donc définir ce que doit nous renvoyer une telle source de donnée, et ensuite jouer nos 
jeux de tests unitaires sur les fonctionnalitées que nous avons développées. 
Mais contrairement au mojito, sans modération :o
En effet il convient, d'avoir un bon test coverage dans la mesure du possible, 
notamment lié au limite de temps mais surtout de budget. Pour le test coverage 
on pourra notamment utiliser Jacoco, allez c'est parti mon coco !

___

<h5 align="left">
  Idées générales :
</h5>

```
○ 1) on mock les ressources externes à la classe que nous testons 'classUnderTest'.
○ 2) on instantie et initialise les données nécessaires à notre 'classUnderTest'.
○ 3) on lance une des méthodes à tester de 'classUnderTest'.
○ 4) on `assertTrue()` que la méthode renvoie bien le résultat qu'elle est censé rentourner.
○ 5) on `verify()` que la méthode à bien été appelé une fois.
○ [FACULTATIF] on check dans un cas spécifique que la méthode à bien lever les exceptions qu'elle est censé lever.
```

___

<h5 align="left">
  Cas classique :
</h5>

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyWonderfulClass.class)
class MyWonderfulClassTest {
    
    // TRUE DATA ///////////////////////////////////////////////////////////////////////
    // constants
    private static final String MUSIC_1_TITLE = "Smooth Criminal";
    private static final String CONSTANT_DATA_MOST_NEEDED = "Thriller";
    ...

    // object
    private static Music music_1;
    ...

    // object container
    private static List<Music> musicList;
    ...

    // initializer
    static {
        music_1 = new Music(MUSIC_1_TITLE, MUSIC_1_LENGTH);
        musicList.add(music_1);
        ...
    }

    // TEST PROCESS OBJECTS ////////////////////////////////////////////////////////////
    @Mock
    MyBDDToMock myBDDToMock;

    @Autowired
    MyServiceToUse myServiceToUse;

    @InjectMocks
    MyWonderfulClass classUnderTest;

    // Initialisation des tests
    @Before
    public void setUp() {
        // Initialisation des annotations Mockito
        MockitoAnnotations.initMocks(this);

        // [FACULTATIF] Par réflexion, on inject les éléments privée dont la classe à tester dépend
        ReflectionTestUtils.setField(classUnderTest, "objectUsedByMyClassUnderTest", 47);
    }

    // UNIT TEST ///////////////////////////////////////////////////////////////////////
    @Test
    public void fooTest() {
        // on mock un résultat normalement retourner par une ressource
        when(myBDDToMock.getMusicTitle()).thenReturn(musicList);
        
        // on lance la méthode à tester
        ... res = classUnderTest.loadMusicInfo();

        // on vérifie la réponse
        assertTrue(res.size() == musicList.size());

        // on vérifie les appels
        Mockito.verify(myBDDToMock, Mockito.Times(1)).getMusicTitle();
    }
```


<h5 align="left">
  [FACULTATIF] Test de la levée des exceptions :
</h5>

```
    // UNIT TEST 4 EXCEPTION ///////////////////////////////////////////////////////////
    @Test
    public void fooTest() {
        ...
        try {
            // on lance la méthode à tester
            ... res = classUnderTest.loadMusicInfo();
            fail("L'appel précédent aurait du lever une exception :/");
        } catch (Exception e) {
            assetTrue(MyException.INTERNAL_ERROR_CODE, e.getErrorCode());
        }
        ...
    }
```