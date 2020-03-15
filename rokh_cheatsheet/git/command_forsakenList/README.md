======================= Git : ========================
===== 'forsaken commands of the dead' sur git : ======

Il peut arriver pour nous développeur d'être étourdi, si si... 
Et dans de tel moment il nous faut trouver des solutions pour annuler 
le fait d'avoir lancé tel ou tel commande sur git !

================ REFLOG OF THE DEAD ==================
Pour annuler les actions suivantes :
Un merge
Un rebase
Une suppression de branche
Ou encore la commande `git reset --hard`

On utilise la commande `git reflog`.
La commande `git reflog` nous donne alors accès aux derniers emplacements 
de notre HEAD, et c'est là que ça devient génial !
Pour faire simple, rien n'est perdu sur git, donc plus aucune peur à avoir !

Voyons ça en détail :

--- un merge ou un rebase c'est pareil ---------------
On vient de merge la mauvaise branche, mais on souhaite annuler ça.
Ou encore on vient de rabase depuis le master mais on aurai pas du le faire pour X raison, et on souhaite annuler ça.

On doit faire :
git reflog
On retrouve le SHA1 du commit voulu (ici 8da3459) et on fait :
`git reset --hard 8da3459`
------------------------------------------------------

--- une suppression de branche -----------------------
SUPPRESSION LOCALE :
On vient de supprimer une branche en local, mais on souhaite annuler ça.
Une branche qu'est-ce que c'est ? C'est juste une position de 
commit donnée. Donc on va juste recréer une branche à partir 
de ce commit là.

git branch -D brancheB
bye bye brancheB
git reflog
On retrouve le SHA1 du commit voulu (ici 8da3459) et on fait :
git branch brancheB 8da3459
Ce qui va recréer la branche brancheB à partir du commit 8da3459

NB : bon c'est bien gentil, mais si on a juste supprimé la branche locale,
il suffit de faire un git branch brancheB et git s'occupe de nous créer la branche ainsi que 
de l'associer au remote.

SUPPRESSION REMOTE :
On vient de supprimer une branche en remote, mais on souhaite annuler ça.
Si on a encore la branche locale, il suffit de faire un simple push depuis la locale.
Si par contre on a perdu la locale et la remote, il suffit de retrouver le dernier commit de la branche, 
via `git reflog`, puis de faire la commande : `git branch brancheB 8da3459`
------------------------------------------------------

--- `git reset --hard` -------------------------------
la commande `git reset --hard`
Elle va venir créer une ligne avec un SHA1 dans le résultat de la commande 
reflog. Donc il suffit d'utiliser cette même méthode `git reset --hard` pour annuler 
la commande `git reset --hard`.
prenons l'exemple suivant :
on souhaite supprimer le dernier commit :
`git reset --hard HEAD~1`
on souhaite finalement retrouver ce commit :
`git reflog`
On retrouve le SHA1 du commit voulu (ici 8da3459) et on fait :
`git reset --hard 8da3459`

=> on reset hard le reset hard :o 
------------------------------------------------------

======================================================
