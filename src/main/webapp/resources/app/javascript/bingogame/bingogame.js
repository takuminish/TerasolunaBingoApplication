function lottery() {
    $("#lotteyStart").remove();
    let randomValue = 0;

    let resultValue = 0;

    let flag = true;

    while (true) {
        resultValue = Math.floor(Math.random() * (99 - 0));
        $(".bingoResult").each(function() {
            if (resultValue === $(this).text()) {
                flag = false;
            }
        });
        if (flag === true) {
            break;
        }
    }

    let count = 0;
    let id = setInterval(function() {
        $("#bingoCandidateTd" + randomValue).removeClass("candidating");
        randomValue = Math.floor(Math.random() * (99 - 0));
        $("#bingoCandidateTd" + randomValue).addClass("candidating");
        if (count > 50) {
            clearInterval(id);
            $("#bingoCandidateTd" + randomValue).removeClass("candidating");
            $("#bingoCandidateTd" + resultValue).addClass("candidated");
            $("#bingoValue").val(resultValue);
        }
        count++;
    }, 100);
}