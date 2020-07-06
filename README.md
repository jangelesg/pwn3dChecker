#  Pwn3dChecker **Version 1.0**
# ';--have YOU been pwned? ![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg) ![](https://img.shields.io/badge/Java-powered-green)


- Check if you have an account that has been compromised in a data breach
- Command Line tool it uses an API v3 (https://haveibeenpwned.com/API/v3) that allows the list of pwned accounts (email addresses and usernames) to be quickly searched via a RESTful service. 

 ---
 ## License and Copyright 
 This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
---
## Author 
- Jonathan Angeles <jangelesg@gangsecurity.com>
- https://github.com/jangelesg/pwn3dChecker
---
## Usage 
  Command Line tool that interact with RESTFull Services from https://haveibeenpwned.com/  
  An API KEY is require to consult some services 
  
- java -jar pwn3dCheck.jar -k xxxxxx-api-key -acc user@domain.com -ba
- java -jar pwn3dCheck.jar -k xxxxxx-api-key -bs
- java -jar pwn3dCheck.jar -k xxxxxx-api-key -b
- java -jar pwn3dCheck.jar -k xxxxxx-api-key -bs -name adobe
- java -jar pwn3dCheck.jar -k xxxxxx-api-key -pa -acc user@domain.com

![](https://github.com/jangelesg/pwn3dChecker/blob/master/info/pwn3dcheck2.jpg)

![](https://github.com/jangelesg/pwn3dChecker/blob/master/info/pwn3dcheck.gif)


