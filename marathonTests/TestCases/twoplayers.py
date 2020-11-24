#{{{ Marathon
from default import *
#}}} Marathon

def test():

    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('New Game')
        click('Two Players')
        assert_p('lbl:Name of second Player', 'Enabled', 'true')
        assert_p('lbl:Name of second Player', 'Text', 'Name of second Player')
    close()
    pass
