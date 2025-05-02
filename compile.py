import sys
import os
import subprocess

def compile_run(args):
    if not os.path.exists("build"):
        subprocess.run(["ant"])
    result = subprocess.run(["java", "-cp", "build", "arbitraryarithmetic.MyInfArith"] + args, capture_output=True)
    
    
    print(result.stdout.decode().strip())
    