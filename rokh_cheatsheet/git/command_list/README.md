======================= Git : ========================
============= Liste des commandes git : ==============

--- Alors ça te branche ? ----------------------------
Créer une branche de featureA :
git checkout -b featureA

Créer une branche de featureA basé sur une branche initiale :
git checkout -b featureA branchInit
=> featureA commencera là ou se trouve le dernier commit de branchInit

Mettre en place le remote de notre branche de feature featureA (pour pouvoir push) :
git push --set-upstream origin featureA

Supprimer la branche de featureA (Si en local seulement) :
git branch -D featureA

Supprimer la branche de featureA (Si en remote et local) :
local :
git branch -d featureA
remote :
git branch -dr origin/featureA

Se déplacer sur une branche brancheA
git checkout brancheA

Revenir à la dernière branche (fonctionne par pair, la dernière branche 
actuelle et la dernière branche où on est allé seront swap à chaque 
appel de cette requête ) :
git checkout -
------------------------------------------------------

--- Merge moi si tu peux ;) --------------------------
Merge une feature terminée dans le master :
Suivre le process suivant :

Aller sur la branche master où l'on souhaite insérer le travail de la branche de feature featureA :
git checkout master
On merge avec l'option --no-ff pour concerver visuellement la trace de la feature dans l'historique de commit git :
git merge --no-ff featureA
Enfin on push sur le remote
git push
------------------------------------------------------

--- Rebase moi si tu veux :o -------------------------
Rebase une de feature à partir de master :
Suivre le process suivant :

Aller sur la branche master qui est la branche dont on veut se baser :
git checkout master
Rebaser à partir de master sur la branche feature featureA (ordre de lecture de la commande suivante) :
git rebase master featureA
=> NB : la commande précédente nous place sur la branche featureA
=> NB 2 : la commande précédente rebase le local, mais pas le remote de featureA
On met à jour le remote de featureA :
git push -f
------------------------------------------------------

--- commit moi tout partout... -----------------------
stage tout son travail :
<pre>git add -A</pre>
commit son travail avec un message simple :
<pre>git commit -m "mon message de commit"</pre>
vérifier que l'on est à jour avec le remote
<pre>git pull</pre>
pusher son travail sur le remote
<pre>git push</pre>

---                                                ---

unstage un fichier :
<pre>git reset NomDuFichier</pre>
unstage tout son travail :
<pre>git reset HEAD</pre>
pour reprendre le travail du dernier commit :
<pre>git revert HEAD^</pre>
(ou)
<pre>git reset --soft HEAD~1</pre>
supprimer un commit en local (on annule ici le dernier commit réalisé) :
<pre>git reset --hard HEAD~1</pre>
annuler un commit en remote == créer un commit inverse (vu que l'on est plusieurs à travailler sur le projet, la règle c'est de faire un revert pour annuler proprement son commit) :
<pre>git revert SHA_duCommit</pre>
commit son travail avec un message en plusieurs paragraphe :
<pre>git commit -m "mon message de commit" -m "mon second paragraphe de commit"</pre>
modifier le message du dernier commit
<pre>git commit --amend</pre>
annuler les modifications effectuées sur un fichier fichierA :
<pre>git checkout -- fichierA</pre>
annuler toutes les modifications effectuées :
<pre>git checkout -- .</pre>
push impossible car pas de remote, il suffit de le créer via la commande suivante :
<pre>git push --set-upstream origin nomDeLaBranche</pre>
------------------------------------------------------

--- le coucou stach stach ----------------------------
Lorsque l'on travail sur de nombreuses choses en même temps, git nous permet de mettre en pause notre travail grâce à la commande :
<pre>git stash</pre>
Par soucis de lisibilité, il vaut mieux lui associer une description, pour cela faire :
<pre>git stash save "ma description"</pre>
afficher la liste des stash
<pre>git stash list</pre>
affiche les différences de manière sommaire avec le contenu du repo actuel
<pre>git stash show</pre>
affiche les différences comme avec un git diff
<pre>git stash show -p</pre>
Réappliquer notre stash et reprendre le travail dessus (prend le dernier stash créé)
<pre>git stash pop</pre>
(ou) si on a plusieurs stash, en remplaçant 0 par l'index affiché par la commande 'git stash list'
<pre>git stash pop stash@{0}</pre>
créer une branche à partir d'un stash (prend le dernier stash créé)
<pre>git stash branch</pre>
(ou) si on a plusieurs stash, en remplaçant 0 par l'index affiché par la commande 'git stash list'
<pre>git stash branch <name> stash@{0}</pre>
supprimer le stash d'index 0, affiché par la commande 'git stash list'
<pre>git stash drop stash@{0}</pre>
supprimer tout les stash du repo
<pre>git stash clear</pre>
------------------------------------------------------

--- l'information est source de pouvoir ! ------------
afficher les logs des derniers commits
<pre>git log</pre>
afficher les différences entre les branches locale et remote
<pre>git diff</pre>
afficher le statut actuelle du repo
<pre>git status</pre>
afficher les derniers emplacements de notre HEAD
<pre>git reflog</pre>
------------------------------------------------------
======================================================
