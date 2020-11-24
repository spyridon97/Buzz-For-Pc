#{{{ Marathon
from default import *
#}}} Marathon

def test():
    set_java_recorded_version("1.8.0_265")
    set_no_fail_on_exit(True)
    if window('Buzz'):
        click('Exit')
    close()

    pass