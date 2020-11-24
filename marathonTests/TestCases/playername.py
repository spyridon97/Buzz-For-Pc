#{{{ Marathon
from default import *
#}}} Marathon

def test():

    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('New Game')
        select('Name of PLayer', 'Makis')
        click('Start Game')
        assert_p('lbl:Makis', 'Text', 'Makis:')
    close()

    pass