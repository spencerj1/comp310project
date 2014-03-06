# 1. Create your local repository

You now need to create a Git repository on your machine to save (commit) your work and get material from me.

Open Git Extensions.

1. Click Create new repository (under Common Actions or Start).
2. Click Browse.
3. In the file selector, click your name, click Make New Folder and type in `comp310project`. Click OK.
4. Leave the repository type alone, it should be Personal Repository as selected by default. Click Initialize.
5. Click Repository -> Remote repositories...
6. In the dialog box, for name, enter: `upstream`; for URL, enter: `https://github.com/lawrancej/comp310project.git`. Click Save changes. Click Yes, and follow any prompts. Close the Remote repositories dialog box.
7. Click the Down arrow button to pull from the professor. You don't need to play with any of the options, just click Pull.

# 2. Create your remote repository

Now create a place to submit your work, called a remote repository. **You need to ensure that your remote repository is private, otherwise I cannot post feedback to you for legal reasons (FERPA).** 

## Bitbucket users

1. [Create a new, empty private repository](https://bitbucket.org/repo/create), called **comp310project**. Don't play with the other settings, just go with the defaults (DO NOT initialize the repository with a README).
2. Once created, you will see your new remote repository. Bookmark this page in your browser, so you can get back to it later.

## Github users

1. [Request private repositories for educational purposes as a student](http://github.com/edu). Github's turn around time is short; however, the start of a semester is the busiest time for this, and an actual person reviews all requests from students.
2. [Create a new, empty public repository](https://github.com/new), called **comp310project**. Don't play around with the other settings, just go with the defaults (DO NOT initialize the repository with a README).
3. Once created, you will see your new remote repository. Bookmark this page in your browser, so you can get back to it later.
4.  When Github confirms that you are a student, make the repository you created private by clicking on the wrench and screwdriver icon (it's **Settings**, not **Account settings**) and scroll down to the **Danger Zone** and click **Make private**. 

# 3. Push to your remote repository

So far, you have created a remote repository, but haven't submitted (pushed) anything to it yet.

## Bitbucket users

Click I'm starting from scratch. Select the SSH URL appearing after `git remote add origin` (it should look something like: `ssh://git@bitbucket.org/yourUserNameHere/comp310.git`; not: `https://bitbucket.org/yourUserNameHere/comp310.git`). Please copy the SSH URL only, not the entire line.

## Github users

Under Quick Setup, select and copy the SSH URL to the clipboard (It should look something like: `git@github.com:yourUserNameHere/comp310project.git`, not: `https://github.com/lawrancej/comp310.git`).

## All users

1. In Git Extensions, click Repository -> Remote repositories...
2. Click New. Under Details, enter `origin` for the name, and paste the URL you copied earlier from Bitbucket or Github.
3. Click Save Changes and then click Close.
4. Click the Up arrow (Push). Click Push.
5. Reload your Bitbucket or Github repository page. If all went well, you will see **COMP310: Data Structures**. 

# 4. Share your repo with me and your friends and watch mine

Right now, nobody but you can see your remote repository; you need to add me and your collaborators as a collaborator so that I can see the work you submit. Also, I will make updates frequently during the semester, and I won't email you when I make changes, so you'll want to watch my repository to receive change notifications.

## Bitbucket users

1. Click the gear icon to administrate your repository.
2. Select **Access management**.
3. Under Users, enter `lawrancej`, and click Add. Do the same for each of your collaborators on the project.
4. [Go to my repository](https://bitbucket.org/lawrancej/comp310), and click the eye icon to watch it for changes.

## Github users

1. Click the wrench and screwdriver icon on the right side of the page (If you hover your mouse over it, it's **Settings**, not **Account settings**).
2. Click **Collaborators**
3. Enter `lawrancej`, and click Add. Do the same for each of your collaborators on the project.
4. [Go to my repository](https://github.com/lawrancej/comp310), and click the eye icon to watch it for changes.

Now we can see each other's stuff.
