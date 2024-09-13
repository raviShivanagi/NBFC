function validate() {
$('#addApplicant').submit(function(){
    $("#submitButton", this)
      .html("Please Wait...")
      .attr('disabled', 'disabled');
});
	var flag = true;
	document.getElementById("applicantFirstNameMsg").innerHTML = "";
	document.getElementById("applicantMidNameMsg").innerHTML = "";
	document.getElementById("applicantLastNameMsg").innerHTML = "";
	document.getElementById("mobileNoMsg").innerHTML = "";
	document.getElementById("emailMsg").innerHTML = "";
	document.getElementById("addressMsg").innerHTML = "";
	document.getElementById("loanAmountMsg").innerHTML = "";
	document.getElementById("interestRateMsg").innerHTML = "";
	document.getElementById("processingFeeMsg").innerHTML = "";
	document.getElementById("fgFirstNameMsg").innerHTML = "";
	document.getElementById("fgMidNameMsg").innerHTML = "";
	document.getElementById("fgLastNameMsg").innerHTML = "";
	document.getElementById("sgFirstNameMsg").innerHTML = "";
	document.getElementById("sgMidNameMsg").innerHTML = "";
	document.getElementById("sgLastNameMsg").innerHTML = "";
	
	var alphaRegx = /^([a-zA-Z]){2,25}$/;
	var mobileRegx = /^([0-9]){10}$/;
	var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var loanAmountRegex = '/^([ A-Za-z0-9_@./#&+-]*)$/';
	var interestRateRegex = /^\$?[0-9]+(\.[0-9][0-9])?$/;
	
	var apFirstName = $('#applicantFirstName').val();
	var applicantMidName = $('#applicantMidName').val();
	var applicantLastName = $('#applicantLastName').val();
	var mobileNo = $('#mobileNo').val();
	var email = $('#email').val();
	var address = $('#address').val();
	var loanAmount = $('#loanAmount').val();
	var interestRate =  $('#interestRate').val();
	var loanTenure =  $('#loanTenure').val();
	var processingFee =  $('#processingFee').val();
	
	var fgFirstName = $('#fgFirstName').val();
	var fgMidName = $('#fgMidName').val();
	var fgLastName = $('#fgLastName').val();
	
	
	if(!alphaRegx.test(apFirstName)) {
		document.getElementById("applicantFirstNameMsg").innerHTML = "Please provide valid Applicant First Name.";
		flag = false;
	} else if(!alphaRegx.test(applicantMidName)) {
		document.getElementById("applicantMidNameMsg").innerHTML = "Please provide valid Applicant Middle Name.";
		flag = false;
	} else if(!alphaRegx.test(applicantLastName)) {
		document.getElementById("applicantLastNameMsg").innerHTML = "Please provide valid Applicant Last Name.";
		flag = false;
	} else if(!mobileRegx.test(mobileNo)) {
		document.getElementById("mobileNoMsg").innerHTML = "Please provide valid 10 digit Mobile No.";
		flag = false;
	} else if(!emailRegex.test(email)) {
		document.getElementById("emailMsg").innerHTML = "Please provide valid Email Id.";
		flag = false;
	} else if(!addressRegex.test(address)) {
		document.getElementById("addressMsg").innerHTML = "Please provide valid Address.";
		flag = false;
	} else if(!loanAmountRegex.test(loanAmount)) {
		document.getElementById("loanAmountMsg").innerHTML = "Please provide valid Loan Amount.";
		flag = false;
	} else if(!loanAmountRegex.test(interestRate) || (interestRate != "" && interestRate > 100)) {
		document.getElementById("interestRateMsg").innerHTML = "Please provide valid Interest Rate.";
		flag = false;
	} else if(!loanAmountRegex.test(loanTenure) || (loanTenure != "" && loanTenure > 100)) {
		document.getElementById("loanTenureMsg").innerHTML = "Please provide valid Loan Tenure.";
		flag = false;
	} else if(!loanAmountRegex.test(processingFee)) {
		document.getElementById("processingFeeMsg").innerHTML = "Please provide valid Processing Fee.";
		flag = false;
	} else if(!alphaRegx.test(fgFirstName)) {
		document.getElementById("fgFirstNameMsg").innerHTML = "Please provide valid First Guarantor First Name.";
		flag = false;
	} else if(!alphaRegx.test(fgMidName)) {
		document.getElementById("fgMidNameMsg").innerHTML = "Please provide valid First Guarantor Middle Name.";
		flag = false;
	} else if(!alphaRegx.test(fgLastName)) {
		document.getElementById("fgLastNameMsg").innerHTML = "Please provide  First Guarantor Last Name.";
		flag = false;
	} else if(!alphaRegx.test(sgFirstName)) {
		document.getElementById("sgFirstNameMsg").innerHTML = "Please provide valid Second Guarantor First Name.";
		flag = false;
	} else if(!alphaRegx.test(sgMidName)) {
		document.getElementById("sgMidNameMsg").innerHTML = "Please provide valid Second Guarantor Middle Name.";
		flag = false;
	} else if(!alphaRegx.test(sgLastName)) {
		document.getElementById("sgLastNameeMsg").innerHTML = "Please provide  Second Guarantor Last Name.";
		flag = false;
	}
	
	return flag;

}
	
function checker(status) {

    // Get form
    $.ajax({
            type: 'POST',
            url: '/applicant/checker',
            data: {
                'status': 'status'
            },
            success: function(text) {
                $(document.body).text('Response: ' + text);
            },
            error: function (jqXHR) {
                $(document.body).text('Error: ' + jqXHR.status);
            }
        });

}