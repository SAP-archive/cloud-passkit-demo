cloud-passkit-demo
==================

This sample demonstrates how-to create Apple™ PassKit passes using [jPasskit](https://github.com/bitzeche/jpasskit).

Quick start
-----------

Clone the repo, `git clone https://github.com/SAP/cloud-passkit-demo`, or [download the latest release](https://github.com/SAP/cloud-passkit-demo/archive/master.zip).

**Note**: Please note that you need to be a member of the Apple Developer Program in order to be able to get the necessary certificates required to properly sign your passes! 

Consequently, the provided sample will not run out-of-the-box. You would need to download/create the required certificate files up front. In order to make the provided test case run you'd need to:

+ create your *Certificates.p12* and store it in the [certs](/src/test/resources/certs) folder
+ create the personalized *wwdr.pem" certificate store it in the [certs](/src/test/resources/certs) folder
+ adjust the values in [pass.properties](/src/test/resources/pass.properties) according to your personal/corporate data


Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, the SAP HANA Cloud - Samples project will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the following format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major (and resets the minor and patch)
* New additions without breaking backward compatibility bumps the minor (and resets the patch)
* Bug fixes and misc changes bumps the patch

For more information on SemVer, please visit http://semver.org/

Authors
-------

**Matthias Steiner**

+ http://twitter.com/steinermatt
+ http://github.com/steinermatt


Copyright and license
---------------------

Copyright 2014 SAP AG

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

