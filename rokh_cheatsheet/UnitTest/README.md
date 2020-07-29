<h3 align="center">
  Spring x JUnit4 x Mockito x Jacoco !
</h3>

</br>
</br>

**Sommaire :**

> [Introduction](#--introduction)
> 
> [Les deux écoles](#--les-deux-écoles-)
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

<h4 align="center">
  Les deux écoles
</h4>

<h4 align="left" style="text-decoration:underline;">
  Le test unitaire que j'ai appris à l'école :
</h4>
Le but est de valider tous les chemins que le code peut réaliser, et que dans tous les 
cas le résultat obtenu soit celui spécifié (valeur retournée ou exception levée).<br/><br/>

```
○ 1) on test de manière atomique le code, cela revient à créer une 
méthode de test par 'chemin' dans une fonction. On doit absolument tout tester, à 
savoir les cas nominaux, les cas d'erreurs, les cas d'exceptions, et enfin en testant 
avec des paramètres vides ou null. Une méthode par chemin.

○ 2) on ne test pas directement une méthode privée, car puisqu'elle est privée, 
il s'agît d'un comportement purement interne à une classe. Personne ne va jamais 
appeler cette méthode privée directement et s'attendre à un résultat précis.
Au lieu de cela il convient de tester les fonctions publiques uniquement. Si les méthodes 
publiques appellent des méthodes privées et fonctionnent comme on le souhaite, alors 
par extension les fonctions privées fonctionnement correctement.
```

<br/>

**Cas concret :** on créer sa propre implémentation d'une HashMap ou d'un BinaryTree, 
il faut donc valider à que chaque méthode fait ce qu'elle doit faire, et également 
penser de manière programmation défensive en vérifiant systématiquement les paramètres 
que l'on a en entrée.

___

<h4 align="left" style="text-decoration:underline;">
  Le test unitaire en entreprise :
</h4>
Le but est de se protéger en cas de refacto du code dans le futur. Si dans 3 mois, je 
modifie le comportement d'une méthode, potentiellement utilisée autre part dans le code, 
alors je risque de casser le bon fonctionnement de l'application dans chaque endroit où 
cette méthode est appelée. <br/><br/>

```
○ 1) on doit donc créer un jeu de test qui vérifie que l'on réalise bien les appels aux 
méthodes voulu et que les résultats dans chaque méthode soient ceux attendus et ce à 
chaque étapes afin d'en créer une sorte de moule géant. Une méthode par chemin. 
Ainsi si demain je modifie le code, je vais nécessairement casser la forme originel 
de mon moule (mes jeux de tests), et donc potentiellement entraîner des régressions, 
d'où le besoin de faire ces jeux de tests. Je n'appelerai pas cela des tests unitaires, 
mais plutôt un moule refacto-préventif hihi :P.

○ 2) on doit mocker nos appels, car on travaille généralement sur des services de haut 
niveau (appel BDD, appel service REST distant, ...) et on ne peut se permettre de 
dépendre de l'existance d'un tier pour valider et confirmer le bon fonctionnement d'une 
des unités que nous avons mis en place.
```

<br/>

**Cas concret :** on créer une classe de service qui nous permet de réaliser des actions 
sur une API WEB. On va donc mocker ce service à distance, et on va se concentrer non 
pas sur le fait que les deux fonctionnent bien de pair (car au final il s'agît plutôt 
de test d'intégration), mais plutôt sur le fait de vérifier que l'on appelle bien nos 
traitement et méthode le nombre de fois prévu, et que les modifications éventuelles 
des données en interne ait étés prises en compte correctement.
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
        // on mock un résultat normalement retourné par une ressource
        when(myBDDToMock.getMusicTitle()).thenReturn(musicList);
        
        // on lance la méthode à tester
        ... res = classUnderTest.loadMusicInfo();

        // on vérifie la réponse
        assertTrue(res.size() == musicList.size());

        // on vérifie les appels
        Mockito.verify(myBDDToMock, Mockito.Times(1)).getMusicTitle();
    }
```

___

<h5 align="left">
  [FACULTATIF] Test de la levée des exceptions - classique :
</h5>

```
    // UNIT TEST 4 EXCEPTION - CLASSIQUE ///////////////////////////////////////////////
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

___

<h5 align="left">
  [FACULTATIF] Test de la levée des exceptions - lambda :
</h5>

```
    // UNIT TEST 4 EXCEPTION - LAMBDA //////////////////////////////////////////////////
    @Test
    public void fooTest() {
        ...
        // on lance la méthode à tester et on vérifie que l'exception attendu est bien levée
        MyException thrown = assertThrows(MyException.class, () -> {
            classUnderTest.loadMusicInfo();
        });
        
        // [FACULTATIF] on check éventeullement le message d'erreur        
        assertEquals(thrown.getMessage(), "my error message");
    }
```