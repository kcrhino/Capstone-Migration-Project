The correction made for Skid2.0's "TeX" file, this addition to the LaTeX files is critical, it will
not work without those additions. 

If you look inside the LaTeX files, these are the lines and additions I have made to all the files.
These Keywords used are used with the hyperref package, and are unique to that package,
using other keywords is tricky, because I ran into a few issues with MySQL complaining about certain
keywords I was trying to use and eventually chose these ones because they are closest to the subject I am pulling out.


line 24 : \usepackage{hyperref}
line 31-36:
\hypersetup{pdfinfo={
Title={SOS Project Reports Skid2.0 Evergreen CTF 2018},
Author={Andrew Jordan, Alexander King, Kreig Clemens, Jacob Golding},
Keywords={PHP, MySql, JavaScript, Hacking, Challenges, Skid2.0, Music4Robots, Resources, Web Security},
Subject={This paper combines multiple resources and challenges that will help the reader’s ability to both understand our way of creating the challenges and to solve them.It dives into web security challenges, and what we learned from reading up on, and creating our own. The goal of our project was to create and host web security and various other challenges on our updated website.}
}}

Having the appropiate key words is FINAL, its how the naming convention works with
pulling out metadata that requires those tags. There are other tags associated with this
and can be used for other purposes.

All the information you can read up at https://ctan.org/pkg/hyperref

No changes visable with the pdf as far as I can tell.
Time required to change files, maybe 3-4 minutes if everything compiles correctly with pdfLatex+BibTex
Also required to compile correctly is the llncs LaTeX class for the micros we used to compile originally.
You can find this file on their website and I believe I have one located in my work files.

This was built with Apaches PDFbox metadata extraction tools:
Copyright [2018] [Kreig Stuart Clemens Jr]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
