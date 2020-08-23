var firebaseConfig = {
    apiKey: "AIzaSyAe_Z9K2nG_kSlloaI2IgJ8v8ACBPT7pUM",
    authDomain: "rich-harvest-3add2.firebaseapp.com",
    databaseURL: "https://rich-harvest-3add2.firebaseio.com",
    projectId: "rich-harvest-3add2",
    storageBucket: "rich-harvest-3add2.appspot.com",
    messagingSenderId: "171454376479",
    appId: "1:171454376479:web:69261d48d2c6153424032a",
    measurementId: "G-CVSFPV3LWS"
};

// initialize the project
firebase.initializeApp(firebaseConfig);
firebase.analytics();

console.log("Firebase app initialized.");