<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    var height = 0;
    var color = 0;
    var double = 0;
    var doubleValue = 0;
    var vulnerable = 0;
    var position = 0;
    var tricks = 0;
    function chooseHeight(choice) {
        document.getElementById('tricks').innerHTML = '';
        if (choice == height && document.getElementById('heightValue').innerHTML != '') {
            document.getElementById('heightValue').innerHTML = '';
            height = 0;
        } else {
            height = choice;
            if (choice == 0) {
                document.getElementById('heightValue').innerHTML = '4 PASY';
                document.getElementById('contractDouble').innerHTML = '';
                document.getElementById('colorValue').innerHTML = '';
                setDisabled(true);
            } else {
                setDisabled(false);
                document.getElementById('heightValue').innerHTML = height;
            }
        }
        document.getElementById('heightInput').value = height;
        checkButtonDisabled();
    }

    function chooseColor(choice) {
        document.getElementById('tricks').innerHTML = '';
        switch (choice) {
            case 'NT':
                color = 'NT';
                break;
            case '♣':
                color = 'C';
                break;
            case '♦':
                color = 'D';
                break;
            case '♥':
                color = 'H';
                break;
            case '♠':
                color = 'S';
                break;
        }
        document.getElementById('colorValue').innerHTML = choice;
        checkButtonDisabled();
    }

    function chooseDouble(choice) {
        document.getElementById('tricks').innerHTML = '';
        if (choice == double && document.getElementById('contractDouble').innerHTML != '') {
            document.getElementById('contractDouble').innerHTML = '';
            doubleValue = 0;
        } else {
            double = choice;
            if (choice == 'x') {
                document.getElementById('contractDouble').innerHTML = 'X';
                doubleValue = 1;
            } else {
                document.getElementById('contractDouble').innerHTML = 'XX';
                doubleValue = 2;
            }
        }
        checkButtonDisabled();
    }

    function chooseVulnerable(choice) {
        document.getElementById('tricks').innerHTML = '';
        vulnerable = choice;
    }

    function choosePosition(choice) {
        document.getElementById('tricks').innerHTML = '';
        position = choice;
        document.getElementById('positionValue').innerHTML = choice;
        checkButtonDisabled();
    }

    function chooseTricksNumber(choice) {
        tricks = choice;
        if (height != 0) {
            var tricksToMade = height + 6;
            var resultTricks = tricks - tricksToMade;
            if (resultTricks == 0) {
                document.getElementById('tricks').innerHTML = '=';
            } else if (resultTricks > 0) {
                document.getElementById('tricks').innerHTML = '+' + resultTricks;
            } else {
                document.getElementById('tricks').innerHTML = resultTricks;
            }
        }
        checkButtonDisabled();
    }

    function setDisabled(val) {
        document.getElementById('c1').disabled = val;
        document.getElementById('c2').disabled = val;
        document.getElementById('c3').disabled = val;
        document.getElementById('c4').disabled = val;
        document.getElementById('c5').disabled = val;
        document.getElementById('d1').disabled = val;
        document.getElementById('d2').disabled = val;
        document.getElementById('d2').disabled = val;
        document.getElementById('v1').disabled = val;
        document.getElementById('v2').disabled = val;
        document.getElementById('p1').disabled = val;
        document.getElementById('p2').disabled = val;
        document.getElementById('p3').disabled = val;
        document.getElementById('p4').disabled = val;
    }

    function setContract() {
        document.getElementById('heightInput').value = height;
        document.getElementById('colorInput').value = color;
        document.getElementById('tricksInput').value = tricks;
        document.getElementById('doubleInput').value = doubleValue;
        document.getElementById('vulnerableInput').value = vulnerable;
        document.getElementById('positionInput').value = position;
    }

    function checkButtonDisabled() {
        if (height == 0 || tricks == 0 || position == 0 || vulnerable == 0) {
            document.getElementById('sendButton').disabled = true;
        } else {
            document.getElementById('sendButton').disabled = false;
        }
    }

</script>

<div class="well" align="center">

    <button class="btn btn-primary btn-lg" onclick="chooseHeight(1)">1</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(2)">2</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(3)">3</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(4)">4</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(5)">5</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(6)">6</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(7)">7</button>
    <button class="btn btn-primary btn-lg" onclick="chooseHeight(0)">
        <spring:message code="label.deal.enterresult.fourpass"/>
    </button>

    <br/>
    <br/>

    <button id="c1" class="btn btn-primary btn-lg" onclick="chooseColor('♣')">♣</button>
    <button id="c2" class="btn btn-primary btn-lg" onclick="chooseColor('♦')">♦</button>
    <button id="c3" class="btn btn-primary btn-lg" onclick="chooseColor('♥')">♥</button>
    <button id="c4" class="btn btn-primary btn-lg" onclick="chooseColor('♠')">♠</button>
    <button id="c5" class="btn btn-primary btn-lg" onclick="chooseColor('NT')">NT</button>

    <button id="d1" class="btn btn-primary btn-lg" onclick="chooseDouble('x')">X</button>
    <button id="d2" class="btn btn-primary btn-lg" onclick="chooseDouble('xx')">XX</button>

    <br/>
    <br/>

    <button id="v1" class="btn btn-primary btn-lg" onclick="chooseVulnerable('1')">
        <spring:message code="label.deal.enterresult.vulnerable"/>
    </button>
    <button id="v2" class="btn btn-primary btn-lg" onclick="chooseVulnerable('2')">
        <spring:message code="label.deal.enterresult.nonvulnerable"/>
    </button>

    <button id="p1" class="btn btn-primary btn-lg" onclick="choosePosition('N')">
        <spring:message code="label.deal.cards.north"/>
    </button>
    <button id="p2" class="btn btn-primary btn-lg" onclick="choosePosition('S')">
        <spring:message code="label.deal.cards.south"/>
    </button>
    <button id="p3" class="btn btn-primary btn-lg" onclick="choosePosition('E')">
        <spring:message code="label.deal.cards.east"/>
    </button>
    <button id="p4" class="btn btn-primary btn-lg" onclick="choosePosition('W')">
        <spring:message code="label.deal.cards.west"/>
    </button>

    <br/>
    <br/>

    <button id="t1" class="btn btn-primary" onclick="chooseTricksNumber('1')">1</button>
    <button id="t2" class="btn btn-primary" onclick="chooseTricksNumber('2')">2</button>
    <button id="t3" class="btn btn-primary" onclick="chooseTricksNumber('3')">3</button>
    <button id="t4" class="btn btn-primary" onclick="chooseTricksNumber('4')">4</button>
    <button id="t5" class="btn btn-primary" onclick="chooseTricksNumber('5')">5</button>
    <button id="t6" class="btn btn-primary" onclick="chooseTricksNumber('6')">6</button>
    <button id="t7" class="btn btn-primary" onclick="chooseTricksNumber('7')">7</button>
    <button id="t8" class="btn btn-primary" onclick="chooseTricksNumber('8')">8</button>
    <button id="t9" class="btn btn-primary" onclick="chooseTricksNumber('9')">9</button>
    <button id="t10" class="btn btn-primary" onclick="chooseTricksNumber('10')">10</button>
    <button id="t11" class="btn btn-primary" onclick="chooseTricksNumber('11')">11</button>
    <button id="t12" class="btn btn-primary" onclick="chooseTricksNumber('12')">12</button>
    <button id="t13" class="btn btn-primary" onclick="chooseTricksNumber('13')">13</button>

    <br/>
    <br/>

    <div class="container">
        <div class="well">
            <label for="heightValue" id="heightValue"></label>
            <label for="colorValue" id="colorValue"></label>
            <label for="positionValue" id="positionValue"></label>
            <label for="contractDouble" id="contractDouble"></label>
            <label for="tricks" id="tricks"></label>
        </div>
    </div>

    <br/>

    <form:form action="${pageContext.request.contextPath}/deals/enterresult?dealId=${dealId}"
               methodParam="dealId" method="post">
        <input type="hidden" name="heightValue" id="heightInput"/>
        <input type="hidden" name="colorValue" id="colorInput"/>
        <input type="hidden" name="tricksValue" id="tricksInput"/>
        <input type="hidden" name="doubleValue" id="doubleInput"/>
        <input type="hidden" name="vulnerableValue" id="vulnerableInput"/>
        <input type="hidden" name="positionValue" id="positionInput"/>
        <button type="submit" id="sendButton" class="btn btn-primary btn-block login-button"
                name="enterResult" value="enterResult" onclick="setContract()" disabled="true">
            <spring:message code="label.deal.enterresult.confirm"/>
        </button>
    </form:form>


</div>