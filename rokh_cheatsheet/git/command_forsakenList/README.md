<h3 align="center">
  Git forsaken commands :
</h3>

</br>
</br>

**Sommaire :**

> [Annuler un rebase ou un merge](#--un-merge-ou-un-rebase-à-annuler-cest-du-pareil-au-même)
>
> [Annuler une suppression de branche](#--une-suppression-de-branche-malencontreuse-)
>
> [Annuler un `git reset --hard`](#--git-reset---hard)

___

Il peut nous arriver, en tant que développeurs d'être étourdi, si si... ¯\_(ツ)_/¯

Et dans de tels moments il nous faut trouver des solutions pour annuler 
le fait d'avoir lancé telle ou telle commande sur git !

<h4 align="center">
   Behold the reflog \o/
</h4>

Pour annuler les actions suivantes :
- Un merge
- Un rebase
- Une suppression de branche
- Ou encore un bon `git reset --hard` bien violent !

On utilise la commande `git reflog` !
La commande `git reflog` nous donne accès aux derniers emplacements de notre HEAD, 
et c'est là que ça devient génial ! Les actions précédentes ont laissées une trace.
Rien n'est perdu sur git, donc plus aucune raison d'avoir peur désormais avec git !

Voyons ça en détail :

___

<h4 align="center">
  Un merge ou un rebase à annuler... c'est du pareil au même.
</h4>

```shell
### On vient de merge la mauvaise branche. 
### (ou) 
### On vient de rabase depuis le master. 
###   => c'était une fausse manip, et on souhaite annuler ça pour X raison.

# On doit alors faire :
git reflog
# On y retrouve le SHA1 du commit voulu (ici 8da3459) puis on fait :
git reset --hard 8da3459
# ...et hop hop ni vu ni connu j'tenbrouille ;)
```

___

<h4 align="center">
  Une suppression de branche malencontreuse... :'(
</h4>

```shell
### SUPPRESSION LOCALE :
### On vient de supprimer une branche en local, mais on souhaite annuler ça. 
### Mais une branche qu'est-ce que c'est ? 
### => C'est simplement un commit à une position donnée. 
###    => Donc on va juste recréer une branche à partir de ce commit là.

git branch -D brancheB
# Bye bye brancheB :'(
git reflog
# On retrouve le SHA1 du commit voulu (ici 8da3459) puis on devrait faire :
git checkout -b brancheB --track origin/brancheB 8da3459
# => mais c'est interdit, on est obliger de la découper en deux commandes :
git checkout -b brancheB 8da3459
git branch --set-upstream-to=origin/brancheB brancheB
# Ce qui va recréer la brancheB à partir du commit 8da3459 et set la remote 'origin/brancheB'.

# NB : bon c'est bien gentil tout ça, mais si on a juste supprimé la branche locale,
# il suffit de faire un git branch brancheB et git s'occupe de nous créer la branche ainsi que 
# de l'associer au remote.

### SUPPRESSION REMOTE :
### On vient de supprimer une branche en remote, mais on souhaite annuler ça.
### Si on a encore la branche locale, il suffit de faire un simple push depuis la locale.
### Si par contre on a perdu la locale et la remote, il suffit de retrouver le dernier commit 
### de la branche, via `git reflog`, puis de faire la commande : `git branch brancheB 8da3459`.
### Ce qui nous recréé la branche en local, ouf on est sauvé !
```

___

<h4 align="center">
  git reset --hard
</h4>

```shell
# la commande `git reset --hard` va venir créer une ligne avec un SHA1 dans le résultat de la 
# commande reflog. Donc il suffit d'utiliser cette même méthode `git reset --hard` pour 
# annuler la commande `git reset --hard`. 
# Prenons l'exemple suivant, on souhaite supprimer le dernier commit en local :
git reset --hard HEAD~1
# On souhaite finalement retrouver ce commit :
git reflog
# On retrouve le SHA1 du commit voulu (ici 8da3459) et on fait :
git reset --hard 8da3459
# Magestic ! on vient de reset hard le reset hard ! :o 
```
