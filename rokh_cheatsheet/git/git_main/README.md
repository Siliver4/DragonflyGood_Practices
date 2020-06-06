<h3 align="center">
  Loué soit le sacro-saint Git !
</h3>

</br>
</br>

**Sommaire :**

> [Introduction](#--introduction)
> 
> [Configuration de Git](#--configuration-de-git)
> 
> [Détails des paramètres du .gitconfig](#--détails-des-paramètres-du-gitconfig)
> 
> [Bonnes pratiques](#--bonnes-pratiques)
> 
> [Security & SSH](#--security--ssh)
> 
> [DON'T TRY THIS AT HOME](#--dont-try-this-at-home)

___

<h4 align="center">
  INTRODUCTION
</h4>

Git est un gestionnaire de version de projet, créé par Linus Torvalds, et on peut le dire,
c'est tout simplement du génie ! Cette petite merveille fait le régale des petits, comme 
des grands...

Bien maitrisé, cette outil se révèle particulièrement puissant, et permet à un groupe de 
gérer ses versions de projet, créer des tag et release pour marquer les moments clés, et 
offre également les outils pour garder un historique de commit propre et limpide, afin 
que toute l'équipe projet puisse travailler ensemble de la meilleur des manières qui soit,
et ce durant toute la durée de vie du projet, et plus, si affinité...

___

<h4 align="center">
  Configuration de Git
</h4>

Git se base sur un fichier de configuration principal pour fonctionner, à savoir 
".gitconfig". Il se trouve par défaut dans votre répertoire de travail :

```shell
cat ~/.gitconfig
```

Une configuration de base de ce fichier serai :

<h5 align="left">
  .gitconfig
</h5>

```
[user]
	email = MON_ADRESSE_MAIL
	name = MON_PSEUDO_GIT
[push]
	default = simple
[pull]
	rebase = preserve
```

MON_ADRESSE_MAIL : votre adresse mail entre guillemet.
</br>
ex : `"dragonflygoodpractice@gmail.com"`

MON_PSEUDO_GIT : votre pseudo git, entre guillemet si vous voulez utiliser des espaces, 
sinon les guillements sont facultatifs.
</br>
ex : `"The Rokh"`

On peut également utiliser les commandes suivantes afin d'éditer directement le fichier `.gitconfig` :
</br>
`git config --global user.email "dragonflygoodpractice@gmail.com"`
</br>
`git config --global user.name "The Rokh"`
</br>
`git config --global push.default simple`
</br>
`git config --global pull.rebase preserve`

Enfin, il est également possible d'utiliser le fichier placé directement dans le répertoire 
du projet (dépôt git), path : `.git/config`. Ce dernier sera lu après celui de votre 
répertoire de travail `~/`, ce qui signifie que les valeurs dans `.git/config` écrasent 
celles dans `~/`. Mais généralement il est préférable de ne pas toucher aux configs du 
fichier `.git/config`.

___

<h4 align="center">
  Détails des paramètres du .gitconfig
</h4>

<h5 align="left">
  PUSH :
</h5>

```	
[push]
	default = simple	
```
=> permet de s'assurer de ne push que la branche locale sur laquelle on se trouve, vers 
la branche distante, puis de la merge.

```
[push]
	default = matching	
```
=> la commande `git push` va venir push toutes les branches locales sur les branches 
distantes puis les merges.

<h5 align="left">
  PULL :
</h5>

```
[pull]
	rebase = preserve
```
=> permet de s'assurer de réaliser un rebase avant le merge. Il faut rappeller que la 
commande `git pull` est en fait un couple de deux autres commandes jouées l'une après 
l'autre :

```
git fetch
```
=> pour récupérer les nouveaux commits sur la branche remote dans notre répertoire de 
travail. Ces éléments ne seront pas fusionnés avec nos commits.

```
git merge
```
=> pour fusionner nos commits avec les éléments récupérés.

Le problème étant que si nous sommes sur une branche de feature, et qu'il y a effectivement 
des commits sur la branche remote que nous n'avons pas en local, alors le merge va avoir 
pour conséquence de créer dans le graphe de l'historique de commit, des commits parallèles, 
ce qui rend la lisibilité de l'historique plus difficile. Pour éviter cela, on préférera 
utiliser un rebase avant de merge. Grâce à ce paramètre `rebase = preserve`, non seulement 
on va réaliser un rebase avant de merge, mais on va également conserver les traces des 
différents merge effectués sur notre branche.
	
___

<h4 align="center">
  Bonnes pratiques
</h4>

<h5 align="left">
  MERGE :
</h5>

Il existe un débat quant à la bonne tenue de l'historique des commits/merges des branches git.
Ce débat ce joue principalement sur les branches de features développées dans un projet.

<h5 align="left">
  MERGE FAST-FORWARD :
</h5>
=> Si on souhaite ne garder aucune séparation visuelle dans l'historique git entre d'un côté 
les features développées et de l'autre les correctifs. 

Alors on va réaliser un merge fast-forward.

Cela nous donne au final une ligne droite du début à la fin du projet, retraçant tout ce qui 
a été fait. Cela permet de garder un historique linéaire des actions effectuées sur le dépôt.
Ainsi tous les commits se suivent d'une manière logique et linéaire, comprenant les 
correctifs, mais également les features développés.

Le premier problème qui apparaît avec une telle stratégie est que si les développeurs ne
choisissent pas des noms pertinent pour leur commit, notamment pour faire la distinction 
entre features et correctifs, alors il deviendra vite très difficile de s'y retrouver dans le
graphe d'historique de git.
Le plus gros problème par contre se joue au niveau des merges, si on souhaite annuler le merge
d'une branche de feature, par exemple pour retravailler dessus, ou tout simplement pour la 
supprimer, tous les commits associés sont fondu dans le reste des commits du projet, si bien 
qu'il faudra à la main tous les annuler. Cela fera autant d'annulation à faire, que la 
feature a eu de commit durant son existance, si bien que cela devient vite un vrai cauchemard 
à gérer. Alors que dans le cas d'un merge `--no-ff` que nous allons voir juste après, seul un 
commit serait à revert.

<h5 align="left">
  MERGE NO FAST-FORWARD (option --no-ff) :
</h5>
=> Si on souhaite garder une trace visuelle des features développées, à l'aide 'des arcs de commits'.

Cela permet de garger un historique chronologique des actions effectués sur le dépôt, avec
potentiellement des commits dits en parallèles ou se chevauchant dans le temps. Par exemple 
si deux personnes travaillent sur deux features différentes en même temps, ce type 
d'historisation va garder l'information sur ce que chacun a fait, mais également sur les 
dates de début/fin ce qui peut entraîner des commits parallèles. 

On utlisera la commande suivante en faisant attention de bien être sur la branche master 
lorsqu'on l'exécute, afin d'y incorporer les éléments de la branche de feature :
```
git merge feature
```

Le problème qui apparaît avec cette stratégie, est que si je commence une feature 
aujourd'hui, et que mon collègue commence la sienne dans 3 jours, si tous les deux nous 
faisons un commit par jour, puis dans deux semaines que l'on merge nos features respectives 
dans la branche master.
Dans l'historique de commit, cela va donner lieu à des commits parallèles, ce qui rend 
difficile le fait de savoir plus tard quel commit à pu engendrer une erreur sur le projet. 
On ne pourra pas forcément remonter à une date exacte, et cela ralentira le processus de 
débug. Heuresement il existe une seconde stratégie à mettre en place afin de pallier à cela, 
il s'agît du :
```
git rebase
```
Toujours se rappeler de faire un rebase avant de faire un merge, et il n'y aura plus jamais 
aucun soucis de commit dit parallèle. Cette méthode du `--no-ff`, si elle est couplé au 
fichier de paramétrage git avec le `[pull] rebase = preserve` est donc parfaite, et permet 
d'obtenir un historique propre, sans se prendre la tête.

```
### NOTABENE
Un merge fast-forward est le comportement par défaut de git si notre branche courrante est à 
jour avec la branche distante.
Par contre si la branche courrante est en retard sur la branche distante, alors le comportement 
par défaut de git sera de faire un merge no fast-forward (aussi appelé : true merge).
```

<h5 align="left">
  REBASE :
</h5>

Rebaser, signifie repositionner l'historique d'une branche de manière à ce qu'elle soit le 
plus à jour possible avec la branche dont elle est partie. Si on rebase la branche de feature 
sur le master, cela signifie que l'on va réécrire l'historique de la branche de feature en se
basant sur celle du master, puis que l'on va appliquer les commits de la branche feature (qui ne sont pas présents sur master) juste après ceux rebasés.

On utlisera la commande suivante en faisant attention de bien être sur la branche master 
lorsqu'on l'exécute :
```git rebase master feature```

Si on a déjà push cette branche en remote (pour sauvegarde par exemple), alors il faudra 
forcer le prochain push avec l'option `-f` car nous venons de modifier l'historique de 
commit en local de la branche de feature.

<h5 align="left">
  QUID DE L'ABSCENCE DE REBASE :
</h5>
C'est une mauvaise pratique que de ne pas faire de rabase.
Si on ne fait pas de rebase, lors des `merge --no-ff` cela va entraîner des commits parallèles 
dans le graphe d'historique, et conplexifier le débug plus tard dans le projet, comme nous 
l'avons vu plus haut. Enfin, puisque l'on peut automatiser le process de rebase, lorsque 
l'on fait un pull, dans 99% des cas on n'a rien de plus à faire que d'exécuter la commande 
`git pull`.

___

<h4 align="center">
  Security & SSH
</h4>

Créer son compte de versionning, par exemple sur github.

Générer votre clé SSH à l'aide de commande suivante :
```ssh-keygen -t  rsa -C "adresse mail"```

Une fois générée, affichez la avec avec la commande suivante :
```cat ~/.ssh/id_rsa.pub```

Copiez son contenu sur le site de versioning voulu, ici github, menu "SSH & GPG keys", et 
ajoutez la à votre compte.

Ajoutez la clé SSH à l'agent d'authenfication "ssh-agent" via la commande suivante :
```ssh-add ~/.ssh/id_rsa```

Pour tester que tout est bon, faire un simple pull :
```git pull```

___

<h4 align="center">
  DON'T TRY THIS AT HOME
</h4>

○ SUPPRIMER LES X DERNIERS COMMIT EN LOCAL SEULEMENT :
╰ Remplacer le chiffre '1' du 'HEAD~1' par le nombre de commit que vous souhaitez supprimer :
<pre> git reset --hard HEAD~1 </pre>

○ SUPPRIMER LES X DERNIERS COMMIT EN LOCAL & REMOTE :
╰ Remplacer le chiffre '1' du 'HEAD~1' par le nombre de commit que vous souhaitez supprimer :
<pre> git reset --hard HEAD~1 </pre>
╰ Puis réécrivez l'historique sur le serveur en forçant la réécriture avec l'option -f :
<pre> git push -f </pre>

<h5 align="left">
  git push -f
</h5>

A éviter sauf cas exceptionel, le `git push -f`

Il s'agît d'un force push, qui va agîr un peu comme un rebase dans le mauvais sens, c'est 
à dire que cela va venir écraser l'historique actuel de la branche visé par celui de notre 
branche. Si bien que l'on pourra faire disparaître des commits d'autres personnes fait 
entre temps !
	
Cas d'utilisation possible :
Si on est sur une branche de feature, et que l'on est sûr d'être seul à travailler dessus, 
alors on peut envisager d'utiliser cette commande si on souhaite annuler des commit que 
l'on aurait push sur le remote et que finalement on ne souhaite plus conserver de trace.
