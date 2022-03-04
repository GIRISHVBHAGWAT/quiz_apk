git add -A

echo your commit
read user_commit

git remote -v
echo your pushing area
read shname
git commit -a -m "$user_commit"

git push $shname
read -p "Enter to exit"


