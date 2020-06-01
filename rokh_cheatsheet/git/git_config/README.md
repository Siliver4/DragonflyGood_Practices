Pour les puristes, il est préférable d'utiliser un template 
lorsque l'on commit, afin que toute l'équipe respecte les 
même pattern et règles. Pour cela on va définir un fichier 
de template, ainsi que l'éditeur de texte (pas de vi/vim/nano 
s'il vous plaît on est pas puristes pas des malades)

------------------------------------------
Dans le fichier '~/.gitconfig' :
(ou)
Via les commandes suivantes :
git config --global core.editor gedit
git config --global commit.template ~/.git_commit_template
------------------------------------------
[core]
	editor = gedit
[commit]
	template = ~/.git_commit_template
	
------------------------------------------
Dans le fichier '~/.git_commit_template' :
------------------------------------------
# main subject - 50 characters
Problem 47: description
# details - 72 characters


# #############################################################
# classic example :
#   # main subject - 50 characters
#   commiting like a true git purist
#   # details - 72 characters
#   Problem
#   We do need a database query to get only rokh's great deeds
#   
#   Solution
#   * Add a column for rokh's deeds
#   * Create a query to keep tracks of the great deeds
#   * Update a style header
#   * Delete some typo in the back
#   
#   Note
#   this example is a bit too long right ;) ?
# #############################################################
------------------------------------------
