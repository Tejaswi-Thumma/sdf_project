import sys
import os
import subprocess

def compile_run(args):
    if not os.path.exists("build"):
        subprocess.run(["ant"])
    subprocess.run(["java", "-cp", "build", "arbitraryarithmetic.MyInfArith"] + args, capture_output=True)
    
    