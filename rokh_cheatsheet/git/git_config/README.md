<h3 align="center">
  Configuration avancée de gitconfig
</h3>

</br>
</br>

**Sommaire :**

> [Template de message de commit](#--commit-message-template)

___

<h4 align="center">
  Le temple des messages de commit :pray:
</h4>

Pour les puristes, il est préférable d'utiliser un template lorsque l'on commit, afin que toute l'équipe
respecte le même pattern et/ou règles adjacentes pour garder une certaine cohérence dans les commits. 
Pour cela on va définir un fichier de template, ainsi que l'éditeur de texte que l'on va utiliser pour 
rédiger le message. On pourra par exemple utiliser 'gedit', ou bien 'sublimetext', mais certainement pas de vi/vim/nano je vous prie, on est plus à l'âge de pierre de l'informatique !

On va avoir besoin de créer un fichier qui contiendra notre template, et on aura besoin de modifier le 
fichier '.gitconfig' afin de lui associer ce template lorsque l'on utilisera la commande `git commit`.

<h5 align="left">
  Dans le fichier '.gitconfig' on va venir rajouter les lignes suivantes :
</h5>

```
[core]
	editor = gedit
[commit]
	template = ~/.git_commit_template	
```

On peut également utiliser les commandes suivantes afin d'éditer directement le fichier `.gitconfig` :
</br>
`git config --global core.editor gedit`
</br>
`git config --global commit.template ~/.git_commit_template`

<h5 align="left">
  Dans le fichier '~/.git_commit_template' :
</h5>

```
# main subject - 72 characters
Problem 47: description
# details - no characters limits


# #############################################################
# classic example :
#   # main subject - 72 characters
#   commiting like a true git purist
#   # details - no characters limits
#   Problem
#   We do need a database query to get only rokh's great deeds
#   
#   Solution
#   - Add a column for rokh's deeds
#   - Create a query to keep tracks of the great deeds
#   - Update a style header
#   - Delete some typo in the back
#   
#   Note
#   this example is a really nice-looking one right ;) ?
# #############################################################
```

Les lignes qui commence par un `#` ne seront pas conservée dans le message du commit. Noter que les tailles que je donne ici pour le titre et le contenu sont parfaitement adapté, mais si par contre vous souhaitez rajouter des détails, comme par exemple une liste à point, afin de reprendre plus précisément les modifications que vous avez faites. Alors il convient de sauter une ligne dans ce fichier et d'écrire ces informations comme dans cet exemple-ci là où j'ai mis "Solution - Add a column ...".

Sauter des lignes permet de suivre le process suivant :
<br>
- 1ère ligne : le titre de votre commit, un bon titre de commit doit être le plus court et le plus explicite possible. Le titre doit faire 72 caractères maximum sinon il va être coupé par des '...' et ce qui dépasse va passer dans le champ suivant 'détail' ce qui peut rendre le message de commit incomplet au premier regard. 
- 2ème, 3ème, ... ligne(s) : le détail, cette partie sert à préciser point par point ce qui a été rajouté/modifié par le commit. C'est une très bonne pratique afin de donner d'avantage d'information sur ce qui a été fait, plutôt que de devoir relire le code directement. Utiliser des tirets '-' est une bonne pratique pour rendre le commit lisible & bien détaillé.

<h4 align="center">
    <br>
      Exemple de rendu visuel dans l'historique de github.com
    <br>
    <br>
      <a href="https://raw.githubusercontent.com/Siliver4/DragonflyGood_Practices/master/assets/img/git/commit_template/commit_template_github_view2.png">
        <img src="/assets/img/git/commit_template/commit_template_github_view2.png" alt="DragonflyGood_Practices_commit_template_github_view2">
      </a>
    <br>
</h4>
