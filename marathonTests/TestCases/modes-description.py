#{{{ Marathon
from default import *
#}}} Marathon

def test():
    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('New Game')
        assert_p('lbl:Correct Answer', 'Text', 'Correct Answer')
        assert_p('descriptionDisplay', 'Text', 'Every player that answers correctly wins 1000 points.')
        click('>')
        assert_p('lbl:Correct Answer', 'Text', 'Stop Timer')
        assert_p('descriptionDisplay', 'Text', 'There is a timer counting down 5 seconds and each player who answers correctly wins gets back as points the remaining milliseconds at the moment that he answered multiplied by 0.2.')
        click('>')
        assert_p('lbl:Correct Answer', 'Text', 'Bet')
        assert_p('descriptionDisplay', 'Text', 'Originally, the category of the question appears. The player can bet 250, 500, 750 and 1000 points. Then, the question is appears and if the answer is correct, the player wins the bet points or forfeit them.')
        click('Two Players')
        click('>')
        click('>')
        click('>')
        assert_p('lbl:Fast Answer', 'Text', 'Fast Answer')
        assert_p('descriptionDisplay', 'Text', 'The first player that answers correctly get 1000 points and the second 500.')
        click('>')
        assert_p('lbl:Fast Answer', 'Text', 'Thermometer')
        assert_p('descriptionDisplay', 'Text', 'The first player who will answer correctly 5 questions gets 5000 points.')
    close()

    pass