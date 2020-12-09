#{{{ Marathon
from default import *
#}}} Marathon

def test():
    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('New Game')
        click('>')
        select('Name of PLayer', 'Makis')
        click('Start Game')
        assert_p('JProgressBar_0', 'Enabled', 'true')
    close()

    pass