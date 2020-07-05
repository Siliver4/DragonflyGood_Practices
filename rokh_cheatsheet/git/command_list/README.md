<h3 align="center">
  Liste des commandes principales git :
</h3>

</br>
</br>

**Sommaire :**

> [Gestion des branches](#--alors-ça-te-branche-)
> 
> [Gestion des rebases](#--rebase-moi-si-tu-veux-o)
>
> [Gestion des merges](#--merge-moi-si-tu-peux--)
> 
> [Gestion des conflits](#--un-conflit-de-canard-on-nen-raffole-pas-tant-que-ça--)
> 
> [Gestion des commits](#--commit-moi-tout-partout)
> 
> [Gestion des erreurs de commit](#--un-commit-un-peu-trop-rapide-_ツ_)
> 
> [Gestion des stash](#--le-coucou-stach-stach)
> 
> [Gestion des informations / logs](#--linformation-est-source-de-pouvoir-)

___

<h4 align="center">
  Alors ça te branche ?
</h4>

```shell
# Créer une branche de featureA :
  git checkout -b featureA

# Créer une branche de featureA basée sur une branche initiale :
# NB: featureA commencera là où se trouve le dernier commit de 'brancheInitiale'.
  git checkout -b featureA brancheInitiale

# Créer une branche de featureA basée sur un commit initial :
# NB: featureA commencera à partir du commit 'commitInitial'.
  git checkout -b featureA commitInitial

# Créer une branche de featureA basée sur une commit initial (ici le commit SHA1 : 8da3459) 
# et qui track un remote 'origin/featureA' (doit être fait en deux commandes) :
  git checkout -b featureA 8da3459
  git branch --set-upstream-to=origin/featureA featureA

# Mettre en place le remote de notre branche de feature featureA :
  git push --set-upstream origin featureA

# Supprimer la branche de featureA (Si en local seulement) :
  git branch -D featureA

# Supprimer la branche de featureA (Si en remote et local) :
# local :
  git branch -d featureA
# remote :
  git branch -dr origin/featureA

# Se déplacer sur une branche brancheA
  git checkout brancheA

# Revenir à la dernière branche (fonctionne par pair, la branche actuelle et la dernière 
# branche où on est allé seront swap à chaque appel de cette commande) :
  git checkout -
```

___

<h4 align="center">
  Rebase moi si tu veux :o
</h4>

```shell
### Rebase une feature à partir de master :
### Suivre le process suivant :

# Aller sur la branche master qui est la branche dont on veut se baser :
  git checkout master
# Rebaser à partir de master sur la branche featureA (c'est l'ordre de lecture de la commande) :
# NB: la commande nous place sur la branche featureA
# NB2: la commande rebase le local, mais pas le remote de featureA
  git rebase master featureA
# On met à jour le remote de featureA (-f car on doit remplacer son historique de commit par le nouveau) :
  git push -f
```

___

<h4 align="center">
  Merge moi si tu peux ;) 
</h4>

```shell
### Merge une feature terminée dans le master :
### Suivre le process suivant :

# Aller sur la branche master où l'on souhaite insérer le travail de la branche de feature :
  git checkout master
# On merge avec l'option --no-ff pour concerver visuellement la trace de la feature dans 
# l'historique de commit git :
  git merge --no-ff featureA
# Enfin on push sur le remote
  git push
```

___

<h4 align="center">
  Un conflit de canard on n'en raffole pas tant que ça :/ 
</h4>

```shell
### Parfois une tentative de merge peut entraîner des conflits :
### Pour éviter les conflits au maximum, penser à faire un rebase avant de faire le merge :o
### Pour régler les conflits, suivre le process suivant :

# Aller sur la branche featureA et lister les fichiers à corriger :
  git checkout featureA
  git status
# Dans chaque fichier listé git nous a créé le pattern suivant :
# 
# <<<<<<< HEAD
# lignes en conflits : ici se trouve celles du fichier sur la branche master
# =======
# lignes en conflits : ici se trouve celles du fichier sur la branche featureA
# >>>>>>> featureA
# 
# On garde au choix, le premier, le second ou bien les deux, ou encore un mixte des deux modifiés 
# à la mano si on a vraiment un conflit avancée. Puis dès que tous les fichiers sont corrigés, il 
# nous suffit de créer le commit marquant la résolution des conflits :
git add -A
git commit -m "Resolving merge conflict"
git push

### Parfois une tentative de rebase peut entraîner des conflits :
### Le process est le même, si ce n'est que l'on n'a pas besoin de faire un commit.
### Une fois les fichiers en conflit gérés, il suffit de faire un :
  git rebase --continue
### Le conflit est bien géré, et le rebase se termine comme prévu.
```

___

<h4 align="center">
  Commit moi tout partout...
</h4>

```shell
# Stage tout son travail :
  git add -A
# Commit son travail avec un message synthétique et clair de moins de 70 caractères :
  git commit -m "mon message de commit"
# Se mettre à jour avec le remote :
  git pull
# Pusher son travail sur le remote :
  git push
```

___

<h4 align="center">
  Un commit un peu trop rapide. ¯\_(ツ)_/¯
</h4>

```shell
# Unstage un fichier :
  git reset NomDuFichier
# Unstage tout son travail :
  git reset HEAD
# Reprendre le travail du dernier commit :
  git reset HEAD^
# (ou)
  git reset --soft HEAD~1
# Supprimer un commit en local (on supprime le dernier commit réalisé) :
  git reset --hard HEAD~1
# Annuler un commit en remote == créer un commit inverse (vu que l'on est plusieurs sur un 
# projet, la règle c'est de faire un revert pour annuler proprement son commit) :
  git revert SHA_duCommit
# Commit son travail avec un message en plusieurs paragraphes :
  git commit -m "mon message de commit" -m "mon second paragraphe de commit" -m "3ème..."
# Modifier le message du dernier commit :
  git commit --amend
# Annuler les modifications effectuées sur un fichier fichierA :
  git checkout -- fichierA
# Annuler toutes les modifications effectuées :
  git checkout -- .
# Push impossible car pas de remote, il suffit de le créer via la commande suivante :
  git push --set-upstream origin nomDeLaBranche
```

___

<h4 align="center">
  Le coucou stach stach
</h4>

```shell
# Lorsque l'on travail sur de nombreuses choses en même temps, git nous permet de mettre en 
# pause notre travail grâce à la commande :
  git stash
# Par soucis de lisibilité, il vaut mieux lui associer une description, pour cela faire :
  git stash save "ma description"
# Afficher la liste des stash :
  git stash list
# Affiche les différences de manière sommaire avec le contenu du repo actuel :
  git stash show
# Affiche les différences comme avec un git diff :
  git stash show -p
# Réappliquer notre stash et reprendre le travail dessus (prend le dernier stash créé) :
  git stash pop
# (ou) Si on a plusieurs stash, en remplaçant 0 par l'index affiché par la commande 'git stash list' :
  git stash pop stash@{0}
# Créer une branche à partir d'un stash (prend le dernier stash créé) :
  git stash branch
# (ou) Si on a plusieurs stash, en remplaçant 0 par l'index affiché par la commande 'git stash list' :
  git stash branch brancheB stash@{0}
# Supprimer le stash d'index 0, affiché par la commande 'git stash list' :
  git stash drop stash@{0}
# Supprimer tous les stash du repo :
  git stash clear
```

___

<h4 align="center">
  L'information est source de pouvoir !
</h4>

```shell
# Afficher les logs de tous les commits (ordre antéchronologique):
  git log
# Afficher les différences entre les branches locale et remote :
  git diff
# Afficher le statut actuel du repo (les fichiers modifiés OU les répertoires) :
  git status
# Afficher le statut actuel du repo (les fichiers modifiés ET les répertoires) :
  git status -u
# Afficher les derniers emplacements de notre HEAD :
  git reflog
```
