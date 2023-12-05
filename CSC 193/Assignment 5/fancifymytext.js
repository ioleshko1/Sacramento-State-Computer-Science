function bigger() {

	// Create a query selector for the information element so we can modify it
	const information = document.getElementById("information");	

	// Change the size of the text in the text box to size 24
	information.style.fontSize = "24pt";

	// Notify the user that they clicked the bigger button
	alert("You pressed the Bigger button!");
	
}

function modifyStyles() {

	// Create query selectors for all of the elements that we want to track
	const information = document.getElementById("information");
	const fancy_input = document.querySelector("#fancy_input");
	const boring_input = document.querySelector("#boring_input");

	// Check to see if the fancy input radio button has been checked
	if (fancy_input.checked) {

		// Make the text bold, blue, and underlined
		information.style.fontWeight = "bold";
		information.style.color = "blue";
		information.style.textDecoration = "underline";

		// Notify the user that they clicked the FancyShmancy button
		alert("You chose the FancyShmancy radio button!");

	}

	// Check to see if the boring input radio button has been checked
	if (boring_input.checked) {

		// Reset the styles back to their original styles
		information.style.fontWeight = "normal";
		information.style.color = "black";
		information.style.textDecoration = "none";

		// Notify the user that they clicked the BoringBetty button
		alert("You chose the BoringBetty radio button!");

	}

}
	
function addMoo() {

	// Create a query selector for the information element so we can modify it
	const information = document.getElementById("information");
		
	// Update the text in the text box to be upper case
	information.value = information.value.toUpperCase();
		
	// Add -Moo to the end of each sentence
	information.value = information.value.split(".").join("-Moo.");

	// Notify the user that they clicked the Moo button
	alert("You pressed the Moo button!");
	
}