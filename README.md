StashPRBldr
===========

Stash Pull Request Builder plugin for Jenkins

### Configuration options

* Build Triggers
   * checkbox: Stash Pull Request (HELP: Trigger builds when a pull request is created or modified.)
* Promotion Action
   * checkbox: Merge/Deny pull request (HELP: If the build succeeds, merge the pull request.  If the build fails, deny the pull request.) 
      * checkbox: Allow ff-only merge (HELP: Only merge the pull request if the merge is a Fast Forward Merge.  Otherwise, deny the pull request.)
   * checkbox: Approve/Decline pull request (If the build succeeds Approve the pull request.  If the build fails, decline the pull request.)
* Builds
   * Merge Pull Request tgt branch into src branch (HELP: Select this step before building the pull request to make certain that the source branch is up-to-date with the latest changes in the target branch. Selecting this infers that the Stash Pull Request trigger, if selected, should to be triggered when the target branch changes as well as when the source branch changes.)
      * checkbox: Rebase instead of merge (HELP: Instead of merging, rebase the target branch onto the source branch. [We will not implement this until we have given more thought to whether it is actually a good idea.])
      * checkbox: Push (HELP: If the merge succeeds, push the merge commit back to Stash.)
