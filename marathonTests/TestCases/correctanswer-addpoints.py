#{{{ Marathon
from default import *
#}}} Marathon

def test():
    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('New Game')
        select('Name of PLayer', 'Makis')
        click('Start Game')
        sleep(2)
        text = get_p('questionDisplay', 'Text')
        sleep(2)
        if text == 'How is called the change of a solid into a gas?':
            click('Sublimate')
        elif text == 'Which is the largest continent?':
            click('Asia')
        elif text == 'What is the currency of Cyprus?':
            click('Euro')
        elif text == 'What is the official language of Brazil?':
            click('Portuguese')
        elif text == "In which city are the FIAT's facilities located?":
            click('Turin')
        elif text == 'Who is the first legislator of Ancient Athens?':
            click('Dragon')
        elif text == 'Which part of the ship is called the bow?':
            click('The front')
        elif text == 'How many books of the Old Testament are written by Moses?':
            click('Five')
        elif text == 'Who composed the famous oratorio "Messiah?':
            click('Handel')
        elif text == 'Where did the Olympics of 1988 take place?':
            click('Seoul')
        elif text == 'Where is sound unable to be propagated?':
            click('In vacuum')
        elif text == 'In what creature did Circe transformed the companions of Odyseeus?':
            click('Pigs')
        elif text == 'Who is the founder of the science of Mathematics?':
            click('Pythagoras')
        elif text == 'Which heresy was condemned by the fourth ecumenical council?':
            click('Arianism')
        elif text == 'Which is the origin of Halloumi cheese?':
            click('Cyprus')
        elif text == 'Which is the smallest planet of our solar system?':
            click('Mercury')
        elif text == 'Which year was the first iphone presented?':
            click('2007')
        elif text == 'When was Alan Turing born?':
            click('1912')
        assert_p('lbl:     1000 Points', 'Text', '1000 Points')
    close()

    pass
