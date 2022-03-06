git config --global user.name GIRISHVBHAGWAT

git config --global user.emailbhagwatgirish337@gmail.com

git init

git add -A

echo your commit
read user_commit

git commit -a -m "$user_commit"
echo pushing githup page link
read link
echo short name 
read name
git remote add $name $link
read -p "Enter to exit"