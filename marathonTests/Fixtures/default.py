'''Launcher uses the properties specified here to launch the application'''

#{{{ Fixture Properties
Fixture_properties = {
        'marathon.project.launcher.model' : 'net.sourceforge.marathon.runtime.RuntimeLauncherModel',
        'marathon.application.mainclass' : 'mainPackage.AppMain',
        'marathon.application.arguments' : '',
        'marathon.application.vm.arguments' : '',
        'marathon.application.vm.command' : '',
        'marathon.application.working.dir' : '',
        'marathon.application.classpath' : '%marathon.project.dir%/../build/libs/Buzz-For-Pc-1.0.0.jar',
        'marathon.recorder.namingstrategy' : 'net.sourceforge.marathon.objectmap.ObjectMapNamingStrategy',
        'marathon.fixture.reuse' : None
    }
#}}} Fixture Properties

'''Default Fixture'''
class Fixture:


    def teardown(self):
        '''Marathon executes this method at the end of test script.'''
        pass

    def setup(self):
        '''Marathon executes this method before the test script.'''
        pass

    def test_setup(self):
        '''Marathon executes this method after the first window of the application is displayed.
        You can add any Marathon script elements here.'''
        pass

fixture = Fixture()
