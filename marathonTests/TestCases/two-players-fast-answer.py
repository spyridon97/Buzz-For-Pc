#{{{ Marathon
from default import *
#}}} Marathon

def correctAnswer():
    sleep(2)
    text = get_p('questionDisplay', 'Text')
    sleep(2)
    answer = []
    if text == 'How is called the change of a solid into a gas?':
        answer = ['Sublimate', '2']
    elif text == 'Which is the largest continent?':
        answer = ['Asia', '1']
    elif text == 'What is the currency of Cyprus?':
        answer = ['Euro', '4']
    elif text == 'What is the official language of Brazil?':
        answer = ['Portuguese', '1']
    elif text == "In which city are the FIAT's facilities located?":
        answer = ['Turin', '3']
    elif text == 'Who is the first legislator of Ancient Athens?':
        answer = ['Dragon', '2']
    elif text == 'Which part of the ship is called the bow?':
        answer = ['The front', '4']
    elif text == 'How many books of the Old Testament are written by Moses?':
        answer = ['Five', '3']
    elif text == 'Who composed the famous oratorio "Messiah?':
        answer = ['Handel', '1']
    elif text == 'Where did the Olympics of 1988 take place?':
        answer = ['Seoul', '2']
    elif text == 'Where is sound unable to be propagated?':
        answer = ['In vacuum', '2']
    elif text == 'In what creature did Circe transformed the companions of Odyseeus?':
        answer = ['Pigs', '3']
    elif text == 'Who is the founder of the science of Mathematics?':
        answer = ['Pythagoras', '1']
    elif text == 'Which heresy was condemned by the fourth ecumenical council?':
        answer = ['Arianism', '4']
    elif text == 'Which is the origin of Halloumi cheese?':
        answer = ['Cyprus', '3']
    elif text == 'Which is the smallest planet of our solar system?':
        answer = ['Mercury', '1']
    elif text == 'Which year was the first iphone presented?':
        answer = ['2007', '1']
    elif text == 'When was Alan Turing born?':
        answer = ['1912', '3']
    return answer

def test():
    set_java_recorded_version("1.8.0_265")
    player1Binding = ['1', '2', '3', '4']
    player2Binding = ['7', '8', '9', '0']
    
    if window('Buzz'):
        click('New Game')
        click('Two Players')
        click('>')
        click('>')
        click('>')
        select('Name of first Player', 'Makis')
        select('Name of second Player', 'Takis')
        click('Start Game')
        answer = correctAnswer()

        answerString = answer[0]
        # we do that to get the proper id
        answerId = int(answer[1]) - 1

        keystroke(answerString, '+' + player1Binding[answerId])
        keystroke(answerString, '+' + player2Binding[answerId])
        
        assert_p('lbl:     1000 Points', 'Enabled', 'true')
        assert_p('lbl:     500 Points', 'Enabled', 'true')
    close()
    pass
