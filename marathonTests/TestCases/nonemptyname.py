#{{{ Marathon
from default import *
#}}} Marathon

def test():
    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('New Game')
        assert_p('Name of PLayer', 'Text', '')
        click('Start Game')

        if window('Name of Player'):
            assert_p('lbl:The name of the player can not be empty!', 'Enabled', 'true')
            click('OK')
        close()
    close()
    pass