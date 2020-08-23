var firebaseCurrentUserData;//hold the signed in current user data

// get the current user data by the listner
firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
        // User is signed in.
        firebaseCurrentUserData = user;
        console.log("user have signed in "+firebaseCurrentUserData.email);
    } else {
        // No user is signed in.
        console.error("user have signed out");
        // document.getElementById("logOutLink").click(); logout the user by clicking logout btn

    }
});
