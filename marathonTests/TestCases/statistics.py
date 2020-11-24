#{{{ Marathon
from default import *
#}}} Marathon

def test():

    set_java_recorded_version("1.8.0_265")
    if window('Buzz'):
        click('Statistics')
        assert_p('lbl: Spiros', 'Text', 'Spiros')
        assert_p('lbl: Spiros', 'Enabled', 'true')
        click('lbl: Takis')
        assert_p('lbl: Takis', 'Enabled', 'true')
        assert_p('lbl: 1', 'Text', '1')
    close()
    pass