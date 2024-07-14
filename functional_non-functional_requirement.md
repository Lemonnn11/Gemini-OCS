6388021 Thanawat Kanjanapoo  

6388090 Kaewalin Limpremwattana  

6388110 Punnavich Thanormvongse  

6388129 Supawit Phimonjit  

6388132 Pawaris Techahongsa  

6388134 Sutthiphon Thankam  


# Gemini-2022
A Gemini project for ITCS431

### General Description
#### 2.5.9 Users of the systems

##### 2.5.9.1 Astronomers and Observers

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| Astronomers and Observers can offer status information both automatically and on request at any required level. | The queue system should be transparent to the on-site observer. | 
| Astronomers and Observers can ask the system about the status of the telescope or any subsystem at any time. | The system must offer a user an interface that fulfilling the various operational requirements in the different modes. |
| The system allows the observer to create an observing program that requests a telescope control function.  | The system should be simple to learn and secure in its use. |
| The system cannot allow the observer to use the command to slew the mount. |  |
| Astronomers are typically given control access to instruments. |  |
| Astronomers and Observers can submit observing commands via UI. | |
| Astronomers and Observers can access the operation table in read mode. | |
| Astronomers and Observers can access and get information about the efficient acquisition of astronomical data and online assessment of data quality. |  |

##### 2.5.9.2 Operations Staff

| **Functional** |
| -------------- | 
| Operations staff can enable direct interactive operation. | 
| Operations staff can control the Gemini 8m Telescopes indirectly via a scheduler program or directly via commands. | 
| The system allows Operations Staff access to monitor general performance and system safety. | 
| Operations Staff can access all commands and maintenance procedures in some cases. | 
| Operations Staff can direct control of physical units. | 
| Operations Staff cannot access subsystems while these are in normal operation. | 
| Operations Staff can access operation tables in update mode. | 
| Operations Staff allows changing the operational status of units (the result id tests performed on such units). | 


##### 2.5.9.3 Software Development and Maintenace Staff
| **Functional** |
| -------------- | 
| Software Development and Maintenance Staff allow for performing system generation and installation of new software packages or new releases. | 
| Software development and maintenance staff need the highest privilege in order to be able to modify everything in the system. | 
| The systems should be allowed Software Development and Maintenance Staff to use some command that is available which is test commands. | 
| The system should be established test and validation procedures.  | 


### 2.6 Operation Context 

Gemini 8m Telescopes program requirements and specifies the operational context.

#### 2.6.1 multi-telescope context

| **Functional** |
| -------------- | 
| Two telescopes as part of the Gemini system can be capable of being used in conjunction with nearby, perhaps non-Gemini, telescopes on the same site (for instance for interferometry). | 

#### 2.6.2 multi-instrument context 

| **Functional** |
| -------------- | 
| The Gemini 8m Telescopes are characterized all the time as in a multi-instru- ment context. Parallel access to all the mounted instruments shall be provided, though only one instrument has access to the telescope beam (active instrument). |
| **Various situations are possible for the other (inactive) instruments:** | 
| They shall be able to take calibration or flat field exposures in parallel. | 
| They shall be able to prepare for an exposure to start as soon as the telescope beam is switched back to them (in this case, they are in a hot standby situation). | 
| They shall be able to work; at all foreseen operation levels (observing, maintenance, testing). | 

#### 2.6.3 visitor instrument context 

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|**The interface should support:**| A subset of the available functionality must be made available through a standardized interface.  |
| - Acquisition of status information of instruments by other systems. | Visitor instruments must be capable of operating in this mode to be adequately supported. |
| - Capability to enter preprogrammed observing sequences. | The system should be able to adapt visitor instruments to standardized interfaces for the Gemini instruments. |
| - Capability to offset the telescope position and focus.| Every decision to change internal standards must not impact external users. |
|  | It is important that the visitor instrument interface be stable and long-lived.|

#### 2.6.4 multi-user context

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| Independently of the location of users at the telescope site, they shall be able to access (according to their privileges) any part of the whole setup with a simple logon and configuration operation. | Monitoring shall not affect the performance of ongoing observations. |
| The system is multi-point monitoring which means while some (active) user is in control of the Gemini 8m Tele- scopes, someone else can follow what they are doing by monitoring all the relevant data from the telescope and instruments. This will typically be needed by the operation supervisor. | 
|  All other users wishing to monitor Gemini 8m Telescopes operations have to go through the procedures set up by Operations and get permission to do so.| 
| The multi-point monitoring mode might also be important when certain difficult or rare problems occur when expert advice is needed and can only be obtained from colleagues situated remotely. Multi-point monitoring allows them to follow directly the results of tests performed and investigate how the system is working (e.g. by selecting different display pages with up-to-date status information on different parts of the system). | 
| Multi-point monitoring also allows a local observer to be monitored and advised by a remote supervisor.| 
| Monitoring shall exist both in the form of automatic displays of status information at different locations and in the form of explicit access to the required status information from any point. | 

### 2.7 Observing Mode Requirements

The different observing modes impose constraints on software design.

#### 2.7.1 Interactive Observing

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| Interactive operation is supported, but always through the Observatory Control System (OCS). | The systems may have more than one station participate in the observation. |
| There is a visual user interface to the OCS to allow for changes to the viewing program.| The telescope operation is supported by the software in a smooth and very friendly way. |
| The initial implementation of the automatic sequencer will operate in a “pass-through” mode. |  |
| Interactive observing with time allocation for full nights is required in the Gemini 8m Telescopes. |  |
| The system is normally executed with the sequencer by providing a computer executable program.| |

#### 2.7.2 Queue-based

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The observing program must be fully automated, requiring very little human interaction during the observation. | Gemini software should be a visually oriented environment providing a simple, easy-to-use interface to the astronomer. |
| There should be a full telescope simulator to enable the astronomer to test observing programs for completeness, errors, and functionality. | The software should have flexible scheduling via a scheduler. |
| All control software must provide support for simulated use within the virtual telescope. |  |
| The software should have the object selection both within an observing program and across observing programs. | |
| The software should consider target positions, weather conditions, and instrument configurations.  |  |
| The observing systems can show the individual observing programs, managing the collection of science, environmental, engineering, reference, and calibration data in transparency. |  |
| The telescope simulator should function within the virtual telescope environment of the Gemini system. |  |
| The systems must queue all of the observings by using the currently available instruments. These would be in the form of preprogrammed observing sequences. 
It should be possible to resort to the queue so that the next observation to take place comes to the front of the queue. This sorting will be based on the properties of the individual observing sequences, current site conditions, and other rules established by the observatory directorate.
 | |

#### 2.7.3 Remote Operations

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| All software should be developed to permit remote operations. There should be no conceptual difference between software working on-site and remotely. | The systems should work both on-site and off-site and do full operations remotely. |
|  | The restricted specific operations should be independent of the operations themselves, and dynamic.|
| | The system will be totally transparent for local or remote use. |
| | The system design should minimize the impact of link bandwidth on transparency. |
| |  The system must be possible to restrict specific operations to specific remote sites. For example, at the Mauna Kea site, remote telescope control might be restricted to Hale Pohaku. |
|  | The system must be secure and might imply different operation levels and privileges at different sites. |
|  | The remote operations software be considered from the beginning in the Gemini 8m Telescopes software design, to avoid redesign later. |

##### 2.7.3.1 Remote Control

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| A staff member must be present and have direct access to hard wired “stop” button, real-time video and audio, and control of the telescope. | The telescope control commands cannot be issued without a staff member which is not necessarily the same person. |


##### 2.7.3.2 Remote Observing

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The user will use a remote User interface to submit commands to the Gemini 8m Telescopes scheduler at the Gemini 8m Telescopes site. | Users will be able to observe from a remote site. |

##### 2.7.3.3 Multipoint Monitoring

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The system appears as a duplicate of that seen by the observer.  | The monitor's keyboard would not have any effect on the observer's environment. |


##### 2.7.3.4 Remote Monitoring

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The system allows the remote user to “pick and choose” the information that is displayed on the remote screen.  |  Remote monitoring should not have any effects on the local user's environment. |
| The systems should have a real-time video and voice link with the operator in the control room. | |

##### 2.7.3.5 Remote Access

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The systems should have a feature to back up local users' expertise and to help in case of problems. | Remote access must be possible from the Gemini 8m Telescopes base facility. |
 
 
#### 2.7.4 Service

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The system should be a visually-oriented environment providing a simple, easy-to-use interface to the astronomer. | The observing program must be automated, requiring little human interaction during the observation. |
| The programming environment should be available both to the astronomer, for developing the program, and to the observer, for review and adjustment of the program.  | The programming environment access may or may not be done concurrently on a shared environment. |
| Service observing requires switching during the night between telescope modes and instruments. This might typically be a few times (2) per night when sky conditions change. | The systems should be smooth running system.|
| The system requires a computer executable observing program. |  |
| The software and data structures to support classical observing which has to be present in a system that is only interactive at the start. |  |

### 2.9 General Software Requirements

#### 2.9.1 Control information flow

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|The syntax of control flow commands is to be consistent across the system, whether accessing workstation software or IOC software. | The support structure for communicating commands must be reliable, with a uniform ACK/NAK protocol adopted across all systems. Timeouts must be supported at approximately 500 msec.|
| All subsystems must respond to a common set of commands to test operational status, inquiries as to version, perform self-tests, etc. | Handshaking of commands between IOCs must occur within 100-200 msec, signaling acceptance of each command. |
| All IOC subsystems must respond to additional common commands for such activities as a start, stop, initialize, reset parameters, etc. | For commands allowing delayed replies, timeouts for that reply must also be supported. |
|  | Peak control information within the system is expected to be 100 TPS. This assumes bridging between communication sections, to isolate traffic in relevant sections only. |

#### 2.9.2 Astronomical data flow

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| **Data acquisition format:** Data is normally acquired as uncompressed data, but may be compressed using a loss-less compression technique for transmission from the Gemini system or across the system LAN.| **Dataflow:** Data from detectors must be stored in the most effective method permitted by available technology.  |
|  | - For focusing and related activities, the maximum acceptable detector readout time is about 0.1 sec, though only a portion of the detector may be read during that time. |
|  | - For mosaicked, large optical detectors, a full readout of the detector must be done in about 2 or 3 minutes. |
|  | **Concurrent data access and display:** Data access must be the capability of providing multiple, simultaneous access to data. Data transfer between the virtual telescope system and attached workstations, therefore, imposes significant transfer requirements on the LAN. The LAN must support a transfer rate of 20-40 Mbits/second.|
|  | **Storage of data:** Data from all instruments and detectors is stored as compressed data, using a standard format.  |
|  | Data is transmitted between Gemini and home Institutes using a FITS format and contains all header information provided with the data.|
|  | **System-wide data capacity:** The system data capacity is capable of retaining 7 days of data produced by the largest instrument, the last 3 days of which must be available interactively from a hard disk or similar medium. |

#### 2.9.3 Video information flow

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|  Video information originates from target acquisition, guiding, and site monitoring cameras |The system must allow for fast transmission of rough images every 0.5 sec. This may be assisted through the use of data-loss compression techniques (e.g. JPEG, MPEG, etc).  |
|  |  there is a need for the transmission of images matching the original resolution. This high-quality transmission must require less than 20 sec, and can only be assisted with loss-less compression. |

### 2.10 Operation Privileges, Protections, and Procedures

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| There must be a system of privileges established at each operating level of the system during logging into the system. | The procedures must be implemented for convenience and system integrity. |
| The systems must use an Access Mode Allocation system that dynamically identifies and assigns resources as needed. Critical resources (those that can support only a restricted number of simultaneous uses) are assigned solely through this allocation system. | |
| **The tasks that require such procedures include:** |  |
| Telescope start-up and shutdown. |  |
| Telescope system self-testing. | |
| Instrument start-up and shut-down. This is not permitted to interfere with telescope operation.| |
| Instrument self-testing and self-diagnosis This is not permitted to interfere with telescope operation. |  |
| Configuration and reconfiguration. | |
| Dynamic reconfiguration of observing configuration (beam switching without restarting instruments and telescope). |  |
| The control software should know what subsystems are installed and their status at all times. | |

### 2.11 General Performance and Reliability Requirement

#### 2.11.1 Capacity

| **Non-Functional** |
| -------------- |
| The Gemini software should have no hard restrictions on the number of simultaneous users but should allow for policy decisions that do restrict the amount of simultaneous access. |

#### 2.11.2 Response Time

| **Non-Functional** |
| -------------- |
| Every command must be accepted/rejected within 2 sec and before the corresponding action occurs.  |
| Status display update must be within 4 sec at the local stations (certain functions, such as telescope position, may have tighter constraints).|
|  Requests of subsystems for status information must be answered within 5 sec and be possible in maintenance-level operation.|
| Requirements for response times within the user interfaces are given in the User Interface requirements section. |

#### 2.11.3 Availability

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| All software bugs should be logged and then fixed as soon as possible after detection. | Fault recovery, exception handling, fail-safe checks, etc. should be used to improve reliability. |

### 2.12 Test and Checkout Requirement

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| The telescope and instrument software shall contain built-in test (BIT) facilities to verify Gemini 8m Telescopes system and Gemini 8m Telescopes software performances. | Regression tests should be a part of every Gemini 8m Telescopes software package. |
| Every Gemini 8m Telescopes software module shall have corresponding test specifications to check the normal operation of releases, to be used both for acceptance tests and as an online test procedure. |  |
| The Gemini 8m Telescopes control software shall also provide for the execution of self-test sequences of the Gemini 8m Telescopes system and subsystems. These shall automatically exercise all subsystems present in a given operational configuration. | |


### 2.13 Contingencies

#### 2.13.1 Fault notification

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| Subsystems must notify the user when faults occur. | The notification must also be capable of being electronically logged. |
| The fault notification must be specific as to the origin and problem. | |
| The notification should prove useful to have multiple levels of fault notification such as detailed, verbose, short, etc. to aid in tracking down problems. | |


#### 2.13.2 Fault Tolerance

| **Functional** | 
| -------------- |
| Should a subsystem fail (e.g. one detector, one instrument) predefined procedures must exist to redefine the environment so that operation can restart with the remaining equipment. |
| In case of computer hardware failure concerning the user station equipment, it shall be possible to transfer control from one user station to another via a simple software reconfiguration procedure. (In this case, there shall be a procedure to replace faulty cards and assemblies.) |
| To observe that particular IOC in a failed state then it must be possible to reconfigure the system to do so. |


#### 2.13.3 Redundancy

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| For each area where redundancy is decided to be cost-effective, procedures for switching to the backup system will be established. There is no requirement for automatic switching to the backup system. | All communication shall be based on the use of standard communication protocols, where retry procedures are applied (a form of software redundancy) as part of the protocol. |

### 2.14 Constraints

#### 2.14.1 User Constraints

| **Non-Functional** |
| ------------------ |
| There should be no restrictions imposed by the software on users. Only policy decisions (permissions, access privileges, etc.) should prevent any user from accessing any part of the Gemini system from any local or remote station.|
| Similar functionality should be presented to the users using similar user interfaces. However, user interfaces should clearly reflect access modes and operating levels.|

#### 2.14.3 Software Constraints 

| **Non-Functional** |
| ------------------ |
| Commercial packages, off-the-shelf public domain software, and standards are to be used whenever feasible. |
| Existing external software will be integrated with the Gemini software. The interfaces involved in this integration are considered part of the Gemini software system. |
| All Gemini software is to be developed using standard methodologies and development environments. One of the goals of Gemini software is that all components be easily (preferably automatically) combined into an integrated system. |
| Gemini software developers should maintain accurate change logs showing software modifications as they are applied to the system software. |
| Gemini software developers should adhere to a standard method for the reporting and recording of errors from both internal and external sources. |
| Gemini software should be developed in an evolutionary fashion, using the CVS version control system. |
| All Gemini subsystem packages should include as part of the software both a simulator module for inclusion in the virtual telescope and user interface modules for the user interface environments that the subsystem will be operating. |
| All Gemini software is to be fully documented, internally with appropriate comments, and external documentation. External documentation must include Unix-style man pages. |
| All Gemini subsystem packages must provide modules for the testing and diagnosis of the subsystem. |
| All instrumentation control software must provide full access to all instrument functionality. It is likely that different user interface modules would present different portions of this functionality to the user. The information required for each interface module is found in the Functional Requirements specifications for each instrument. |
| All Gemini software must be version labeled, both in source and binary form. The version information is to be retrievable from executing software via control commands. |

#### 2.14.4 Design Constraints 

| **Non-Functional** |
| ------------------ |
| There are different requirements for software running on different layers. For example, strict real-time control is restricted to the IOC layer. |
| The integrated system can be tested and developed independently of the target hardware. This is useful not only in the use of the telescope simulator during science planning but in maintenance and testing as well.  |
| All hardware subsystems must provide a software simulation module that responds in a reasonable fashion to commands directed at that hardware. This simulation may require a standard environment, such as VxWorks, EPICS, and VME crate/CPU, but it cannot require any hardware specific to the application.|
| The Gemini subsystem should be as self-contained and autonomous as possible. |
| No subsystem package should make any assumptions about the surrounding environment beyond that provided in the interface specifications. |



### 3. General Requirement

#### 3.1 Data Specifications

| **Non-Functional** |
| -------------- |
|Delay times for the exchange of control information must stay within precise time limits to be defined in “General Description” in Chapter 2.|
|One can afford to retransmit commands in case of transmission error or collision, but the protocol has to be predictable in that commands cannot get lost and replies have to come back reliably.|
|In a number of cases, synchronization with the Time Reference System at the Gemini 8m Telescopes site is also necessary.|
|Access to control parameters, telescope and instrument information for monitoring or other use makes a significant contribution to the control flow, and may be logged at quite high rates for short periods.|

##### 3.1.1 Control Information Flow

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|Control information must be transferred, typically in the form of commands and replies from users, to telescopes and instruments.|Control information on all controlled variables must be provided by all subsystems on request. |
|It must also be possible to restrict user access to the meteorological information coming from a weather station should be available centrally. |It is explicitly required that all such information is available to the Gemini 8m Telescopes software and is capable of being available to all users of the Gemini 8m Telescopes, subject only to restrictions with respect to updating. |

##### 3.1.2 Astronomical Data Flow

###### 3.1.2.1 Data Flow

| **Non-Functional** |
| -------------- | 
|Detector data must be acquired and stored in the most effective way technology.|
|Intermediate storage of raw data in memory on different nodes and in different formats should be kept to a minimum.|
|There must be at least two copies - one to secure data as acquired and one to do assessment of data quality on-line (this last copy preferably on removable media).|
|The link chosen to transfer data should represent as small a bottleneck as possible for data acquisition.|

###### 3.1.2.2 Format of Data Acquisition

| **Non-Functional** |
| -------------- | 
|There might be cases which preprocessing is needed and where, therefore, raw data will be stored in a preprocessed format.|

###### 3.1.2.3 Transport Data Format

| **Non-Functional** |
| -------------- | 
|Astronomical data will have to be transported between GEMINI and the home institutes of visiting astronomers in FITS format.|

##### 3.1.3 Other Information Flow

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|It is a requirement that voice connectivity, perhaps point to point, be available on a permanent connection. Other astronomical information shall also be capable of being available.|TV data concerning site monitoring and voice need to be capable of being available at all operations facilities.|

#### 3.2 Operation

##### 3.2.1 Operation levels
The operation levels contain three main different levels which execute the operation of telescopes, instruments and subsystems and each of them connect to variables, which can be entered particularly by distinct types of users of each level,  to determine the status and manipulate the system.

###### 3.2.1.1 Observing Level
| **Functional** | 
| -------------- |
|The observing is the normal operation mode of the system in which it authorizes a definite amount of embedded tests and monitoring by authorized access. In addition to authorization, this mode can be accessible by all user types.|

###### 3.2.1.2 Maintenance Level
| **Functional** | 
| -------------- |
|The maintenance level is involved with maintenance procedures in which  authorize operations and development staff to update the maintenance tables.|

###### 3.2.1.3 Test Level
| **Functional** | 
| -------------- |
|This level approves users to install and test new packages or new releases as well as update all non-protected variable values with high limits accessible. Moreover, This mode allows each of low level tests to be executed as well.|

##### 3.2.2 Privilege and Protection levels

###### 3.2.2.1 Privileges

| **Functional** | 
| -------------- |
|The privileges level allows privilege involved with a group of users or the location of the user station to more subdivide in levels.|

###### 3.2.2.2 Protections

| **Functional** | 
| -------------- |
|This level enforces protections between users and the operational software should obviously identify the present operational level to users and verify acceptable rapport among subsystems in distinct modes. |

##### 3.2.3 Capacity

| **Non-Functional** |
| -------------- | 
|The capacity of the system can be expressed in terms of nodes that defined as the number of workstations,  in terms of sum total users at all nodes.|
|Each node will have the capability to run at all operation levels.|
|Single operator node for the telescope and two data acquisition and instrument control nodes.|
|Some tests might be run in parallel on instruments that do not have the light beam at that moment, so in principle additional nodes might be working at the same time.|
|The system must support off-site observing modes.|
|The system will provide for a single off-site data acquisition and instrument control node.|
|The system will provide for a single local monitoring node and a single remote monitoring node.|
|Allow simultaneous operation of up to six active control nodes and up to two more monitoring nodes.|
|Ability of coping with the load 10 active nodes.|

##### 3.2.4 Performance Criteria

| **Non-Functional** |
| -------------- | 
|Every command must be acknowledged in a positive or negative way before the occurrence of the corresponding.|

##### 3.2.5 Procedures

| **Non-Functional** |
| -------------- |
|It must be automatic procedures to implement startup and shutdown of the telescope and instruments.|
|Allow a startup and shutdown of instruments independently of the telescope and without affecting the telescope operation.|
|Reconfiguration procedures must exist, to change the observing environment.|
|The definition of the observing environments must be dynamic.|

#### 3.3 External Interface Requirements

##### 3.3.1 User Interfaces (UIF)

| **Non-Functional** |
| -------------- |
| It is essential for operational and maintenance reasons that, in spite of the obvious differences of the setups and commands available, the same philosophy is applied throughout due to the large number of instruments, there can be many different stations which are active at the same time.|
|The user interface should not be seen as a package linked to a specific computer. Given the requirement to be able to access the Gemini 8m Telescopes from several points, the user interface should rather be seen as a package to be callable from a large number of stations, depending on where a user is.|
|It should also be network transparent so that it does not matter where it is being run.|
|The user interface tools shall be based on standards, which will be portable across different computer hardware platforms (Portable User Interface Toolkit).|

##### 3.3.2 Hardware Interfaces 

| **Non-Functional** |
| -------------- |
|The existence of hardware standards is clearly essential for maintenance and repairs and It is also essential to avoid software duplication, and to simplify the Gemini 8m Telescopes software.|
|Microprocessor software in particular tends to contain hardware specific software, though one should try to keep it as hardware independent as possible, isolating different software layers.|
|The standard software must be adequate for the real-time requirements and must offer drivers to the standard electronics to be used on all the Gemini 8m Telescopes software subsystems and instruments.|

##### 3.3.3 Software Interfaces

The software of the Gemini 8m telescopes comprises all perspectives of manipulation and data collection involved with the telescope, instruments, and auxiliary instrumentation as well as all the operation perspectives which consists of on-line scheduling and rescheduling. The software is mentioned as external, even though it is associated with the Gemini 8m Telescopes.
The external software including:

•commercial software incorporated into the Gemini 8m Telescopes software (e.g. DBMS)  

•The core OCS software used in the Gemini 8m Telescopes (e.g. image processing systems, star catalogs)  

•software involved with visitor instrumentation  

•embedded software dedicated to hardware control, but not communicating on-line with the other Gemini 8m Telescopes software (in general this would be microprocessor code)

The software of the Gemini 8m Telescopes must connect to the external software as well and obviously the interfaces are entirely components of the Gemini 8m Telescopes software.

###### 3.3.3.1 On-line Image Processing Interfaces

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|The system should verify the quality of the input astronomical data as on-line image quick-look analysis is needed.|The system should furnish quick-look data processing on the Gemini 8m Telescopes with processes applicable for fast on-line data preprocessing.|
|Advanced pipeline processes are required to make quick-look analysis possible for the Gemini 8m Telescopes partially for observation of a common nature.|The data reduction should operate respectively request by requests especially asynchronously from data gain.|
|Quick-look should be applicable inside exposure sequences to furnish outcomes and feedback variables to the operational software in a programmed characteristic as well as should be synchronous as well.|The Gemini 8m Telescopes site should have Off-line pixel processing for entire data reduction, however it must not have any connection to the Gemini 8m Telescopes software.|
||Accepted reduction process should be accessible for fundamental on-line tests of the observed data.|

###### 3.3.3.2 On-line access to catalogues and previous data

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|Computer access to star catalogues is also needed, so that an automatic selection of candidate guide and standard stars can be made.|The output format of the Gemini 8m Telescopes data must be compatible with the GEMINI archive requirements.|
||Complete raw data set and the interval in which such data will be made available within the same night, weekly established by the Gemini Archiving Requirements.|
||on-line interactive access to the data archiving system should exist, so that access to this database is possible for Gemini 8m Telescopes users as comparisons with previous data might be of great value and affect the actual observing program.|
||A prerequisite for this is that acquired data are made available as directly as possible in a common format, and that all additional.|
||data related to an exposure and logging information are made available on-line at the same time.|
||Near-line processing should be available for simple data reductions required for data integrity validation.|
||It should also be noted that some Gemini 8m Telescopes subsystems, such as adaptive optics, may require their own special on-line pixel processing software.|
||specific observer support software has to be foreseen for on-line use to avoid as far as possible duplication of development effort.|

###### 3.3.3.3 Access to other packages

| **Functional** | 
| -------------- |
|The Gemini 8m Telescopes software must be able to connect with all commercial software packages available on the Gemini 8m Telescopes and integrated into the Gemini 8m Telescopes operation.|

##### 3.3.4 Communication Interfaces

Being in itself a distributed telescope system and having a large number of instruments, the Gemini 8m Telescopes system has internal communication needs.

###### 3.3.4.1 Local Area Network (LAN)

| **Non-Functional** | 
| -------------- |
|The LAN shall support the majority of the Gemini 8m Telescopes system internal communication needs.|
|This LAN must be capable of dealing both with the data bandwidths required (at peak and on average) and with the required response times and synchronization needs.|
|This LAN shall be supplemented with a Local Time Bus, for distribution of absolute and relative time signals, and both a digital reflective memory bus and an analog event-based bus, for distribution of signals with requirements not satisfied by a LAN.|
|To eliminate conceptual access problems, while coping with different bandwidths, LAN and WAN interfaces shall be homogeneous and shall be based on standards which allow migration on different media.|
|Network redundancy should also be considered in the design phase as a way to increase reliability and security, in particular for control information.|
|Due to the uncertain future of the Internet, only non-essential tasks may employ it. All essential tasks, not including remote observing, must take place on resources controlled by the project (such as leased lines).|
|Peer-to-peer connectivity should only be used to overcome a demonstrated performance problem.|
|Bypassing the hierarchy (connected between grandmother and granddaughter with no path through the mother) should only be used for transmission of status information or bulk data, not control flow.|

#### 3.4 General Constraints

##### 3.4.1 User Contraints

| **Non-Functional** | 
| -------------- |
|It will envisaged that observing astronomers who have travelled to the Gemini 8m Telescopes site will make use of the Gemini 8m Telescopes control room facilities.|
|Allow centralized support and coordination of their operations, providing both operations support for individual instruments and supervision.|
|Users can installing or enhancing other parts of the system, possibly working directly at the telescope.|
|Remote operations, the software shall support access to the system from any user station.|
|Operational decision(privileges and priorities of user).|

##### 3.4.2 Hardware Contraints

| **Non-Functional** | 
| -------------- |
|Computers used at the Gemini 8m Telescopes site, particularly in the test phase when they are outside the control room and near the subsystem under test, shall be checked against altitude and humidity specifications for the Gemini 8m Telescopes site.|
|Computer hardware must be able to run the Gemini 8m Telescopes software environment and provide compatibility in data format.|
|Common development and implementation tools must be both available and supported.|
|Identical network access must be supported.|
|Local processing power must be such that telescope and instrument control does not represent a significant overhead in the whole process of executing an observing program.|
|Scalable hardware architecture with computers at various performance levels should solve the problem of adequate on-line data assessment, as the amount of this activity is very variable and dependent upon the kind of detector and method used.|
|Limited bandwidths which may be available remotely, there will be constraints on the functionality of remote operations and access.|
|Remote operation must include remote monitoring from the Gemini 8m Telescopes base facilities, together with access.|

##### 3.4.3 Software Contraints

| **Non-Functional** | 
| -------------- |
|Individual instruments must be able to run fully independently.|
|Telescope software at the two telescopes must be maintained to be identical in the upper layers.|
|Modifications should be confined to the operational procedures and should not affect the bulk of the existing software.|
|Switching to different configurations must be possible at any time with appropriate procedures.|
|There must be easy procedures to reconfigure the system when subsystems are modified or removed.|
|The number of main packages of software must be kept to a minimum to facilitate maintenance, but compatibly with the need to have the right degree of modularity.|
|Commercial and public domain packages should be used whenever possible.|
|Existing software packages should be reused wherever possible.|
|Existing software expertise should be consulted whenever possible.|
|All software which does not directly control specific hardware must be written as machine independent, portable code.|
|To allow for expansion and maintenance, Gemini 8m Telescopes standards must be defined for the on-line software and the development environment.|
|On-line version control must be implemented. That is, the version control system must be available to recover/restore versions at all times.|
|Boot time, the Gemini 8m Telescopes software shall check the consistency of versions of all the various software components.|
|Table-driven software should be used whenever possible, to avoid unnecessary compilations.|
|System status parameters will be maintained to an extent that will allow restarting the system and regaining the previous state.|

#### 3.5 Attributes

Software and control systems produced as part of the Gemini Project Work Packages exhibit numerous common attributes.

##### 3.5.1 Simplicity

| **Non-Functional** | 
| -------------- |
|Include Rick’s information about complexity criteria, show that we are aiming to achieve as simple as system as we can which meets the requirements.|

##### 3.5.2 Supportability

| **Non-Functional** | 
| -------------- |
|Supportability plan will be part of the Gemini Control System.|
**Goals**
  |- Have supportability influence design|
  |- Translate availability and readiness requirements into supportability requirements|
  |- Identify and plan for necessary support|
  |- Provide support at minimum cost|


##### 3.5.3 Reliability and Availability

###### 3.5.3.1 Reliability and Availablity Requirement

| **Non-Functional** | 
| -------------- |
|Guarantee maximum availability of the control system, retry procedures must be embodied in the software in case of error or failure to achieve recovery on-line whenever possible.|
|Should recovery fail, the error or failure has to be reported in a clear form and the system shall put itself into a safe state.|
|Must be possible for the system to reconfigure itself in order to continue observing, in a different mode if required, given the failure of a single non-critical subsystem.|
|Range checking and validity checking shall be supported before execution of any input command to increase software robustness.|
|Must be possible ahead of time, preparing observing sequences for automatic observations and simulating observations to estimate results.|
|On-line pre-checking of the operational status of equipment should be done prior to sending critical or time-consuming commands.|
|Must be possible to apply continuous monitoring to all subsystems on request, both when in operation and when idle, to check their operational status.|
|Measure of fault rates should be done during commissioning to establish baseline rates for system reliability monitoring.|
|Recovery procedures to restart after error failure.|
|The system should be constantly monitoring active subsystems to be sure they are operating correctly before sending command to each subsystem.|
|This monitoring should continue on inactive subsystems.|
|Recover and reconfiguration is 5 minutes from onset of the error condition to observing again.|

##### 3.5.4 Maintainability

| **Non-Functional** | 
| -------------- |
|Including an estimate of required resources.|
|When upgrading the system to add capabilities and performance. Areas where upgrades are anticipated should be identified with an estimate of the required effort and resources.|
|It will be delivered with the control system.|

###### 3.5.4.1 Maintenance Requirements

| **Non-Functional** | 
| -------------- |
|The work package is the responsibility of the WPR and the maintenance costs are not covered by the work package budget.|
|All subsystem software is include modules to aid in the maintenance and testing of the subsystem.|
|Community software support (EPICS) is available nominally free-of-charge through the normal release and bug-fix procedures used in the community.|
|Simple mechanisms should exist for replacing a subsystem with simulation.|

###### *Minitor level*

| **Non-Functional** | 
| -------------- |
|Each subsystem should have a background task running whenever that subsystem is operational, performing such tasks as checking power supply levels, temperatures, performance, and correct responses to commands.|

###### *Self-test level*

| **Non-Functional** | 
| -------------- |
|Each subsystem should provide a module for fully exercising all subsystem components, both hardware and software.|
|Executed automatically during start-up and on demand through the defined interface.|

###### *System level*

| **Non-Functional** | 
| -------------- |
|Software modules for testing the subsystem as an integrated portion of the entire system.|

###### 3.5.4.2 Levels of Maintenance

###### *3.5.4.2.1 Organizational level*

| **Non-Functional** | 
| -------------- |
|repair by unit replacement|
|repair units by module replacement|

###### *3.5.4.2.2 Intermediate level*

| **Non-Functional** | 
| -------------- |
|repair by module replacement|

###### *3.5.4.2.3 Depot level*

| **Non-Functional** | 
| -------------- |
|This could be done at a Gemini 8m Telescopes base facility during the day.|
|module repair|

###### *3.5.4.2.3 Contractor*

| **Non-Functional** | 
| -------------- |
|Done at the contractor/vendor’s site.|
|repair or replace|

###### 3.5.4.3 Maintenance Goals

| **Non-Functional** | 
| -------------- |
|downtime due to maintenance|
|cost of maintenance|
|numbers and skill levels of personnel|
|efforts to perform maintenance|
|errors in maintaining systems|
|failures induced in maintenance|

###### 3.5.4.6 Quantitive Maintenance Requirements

| **Non-Functional** | 
| -------------- |
|**Allocated to the system, subsystems, and each component:**|
|Mean Time to Repair for each maintenance level.|
|Maximum Time to Repair for each maintenance level.|
|Preventative Maintenance hours per year.|

###### *3.5.4.6.1 Maintenance Interval*

| **Non-Functional** | 
| -------------- |
|All equipment shall support a programmed adjustment and maintenance interval of 30 days or longer.|

##### 3.5.5 Human Engineering

| **Non-Functional** | 
| -------------- |
|provisions for minimizing stress effects and fatigue|
|feedback on operation on specific tasks|
|people and machine interfaces|
|procedures|
|training and experience|
|interaction with team members|
|management and organizational behavior|

##### 3.5.6 Security and Safety

###### 3.5.6.1 Definition of Safety and Risk

| **Non-Functional** | 
| -------------- |
|Safety: Freedom from those conditions that can cause death, injury, occupational illness, or damage to or loss of equipment or property.|
|Risk: An expression of the possibility of a mishap in terms of hazard severity and hazard probabilit.|

###### 3.5.6.2 Security and Safety Requirements

| **Non-Functional** | 
| -------------- |
|Self-monitoring to invoke safety monitoring to prevent risk to people or damage to equipment.|
|The software should be able to quickly bring the Gemini system to a safe state upon notification of such danger.|
|Subsystems must be able to detect such danger and report it appropriately.|
|**The order in which these systems will work is as follows:**|
|software limits: the software will not allow unsafe actions, the command will be rejected.|
|soft limit switches: the software will detect unsafe areas and halt, the software will allow movement off of soft limits.|
|software watch dogs: the software will halt if its watch dog has not been reset.|
|hardware watch dogs: the system will halt if its watch dog has not been reset.|
|hard limit switches: these switches will remove power from actuator when beyond soft limits, the software/electrical systems will allow movement off of hard limits.|
|hardware interlocks: these will prevent both software and hardware from action. Here will be no bypass of these systems.|
|hard stops: the mechanism cannot move beyond this point due to mechanical limit(s). In general hard stops will use dampers to avoid damage to equipment.|
| ----------------------------------------------------------------------------------------------------------------------------|
|All necessary safety approvals will be obtained before devices will be accepted.|
|Engineering/Maintenance mode must ignore directives from other systems, though status information should still be provided for use by other systems.|
|must be provided in order to both prevent accidental mix-up of commands from different users on different parts of the system and to prevent intrusion from the wide area network into the Gemini 8m Telescopes.|
|able to bring the Gemini 8m Telescopes system quickly to a safe state upon detection of danger.|
|the astronomical database must be protected from intrusion.|
|access private data or to be destructive.|
|provide intrusion security by a well designed network gateway acting as a firewall.|
|The security prevents the intrusion into the system by unauthorized users, or users at unauthorized access levels.|
|All systems are appropriately interlocked, interlock must not depend on any software for reliable operation|
|**The general safety requirements:**|
|eliminate hazards through design, including material selection|
|isolate hazardous substances from people|
|minimize hazard to people during operation and maintenance from high voltage, electro- magnetic radiation, sharp edges, hot surfaces, chemicals, etc.|
|minimize risks due to environmental conditions, such as temperature, noise, vibration, etc.|
|minimize risks created by human error|
|use interlocks and other protective devices when hazards cannot be eliminated|
|provide distinctive markings and warnings to protect people|
|**The interlock philosophy:**|
|All hazards capable of causing death and/or loss of irreplaceable equipment shall be passively interlocked.|
|All hazards capable of causing injury and/or severe damage to equipment shall be actively interlocked depot level.|
|All other hazards may be interlocked via software.|

##### 3.5.7 Testability

| **Non-Functional** | 
| -------------- |
|It will address all areas of testing from design, acceptance, commissioning through to hand-over.|
|Integral capability of the mission equipment which provides an onboard, automated test capability to detect, diagnose, or isolate system failures(Built-in-tes).|
|The require have testability and Built-in-test definitions.|

##### 3.5.8 Expandability

| **Non-Functional** | 
| -------------- |
|The software installation process and documentation must be developed with expandability in mind, using general industry standards.|

##### 3.5.9 Modularity

| **Non-Functional** | 
| -------------- |
|The software must be strictly modular.|
|Each module’s environment is strictly defined by its interface to other components.|
|Module selection should be done in logical fashion to minimize the size of the interfaces between modules.|
|The on-line databases can be considered part of this interface, but are only accessible through their defined interfaces.|
|Multi-instrument operation, must exist to acquire information about other parts of the system.|
|No undesired interactions between subsystems.|

##### 3.5.10 Contingencies

| **Non-Functional** | 
| -------------- |
|The security and safety of the system should be guaranteed even failure of any component, also the higher-level software.|
|Reconfigure the software if one actuator fails it still desirable.|
|Data redundancy, prevent a single failure from lossing collected data.|
|minimize the effects of single-point errors throughout the system.|

##### 3.5.11 Concurrency

| **Functional** | 
| -------------- |
|The Telescope Control System should be capable of detecting and invoking parallel operation as it is responsible for control of all of the telescope and enclosure subsystems.|

### Specific Requirement 

#### 4.1 ATTRIBUTE

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| Preventative maintenance is scheduled as specified in the Gemini Design Requirements Specification. | The Gemini system maintenance philosophy is described in the Software Management Plan (SMP).|
| During science planning, there should be validity and feasibility checks to help ensure effective and efficient use of the telescope. Where appropriate, these checks should also be performed during operation. | All software is to be developed using typical modularization and standardization techniques. In particular, each module's environment is strictly defined by its interface to other components. No module can rely upon information outside of this interface. Module selection should be done in logical fashion to minimize the size of the interfaces between modules. |
| The system should be constantly monitoring active subsystems to be sure they are operating correctly before sending commands to each subsystem. This monitoring should continue on inactive subsystems | The on-line databases can be considered part of this interface, but are only accessible Reliability and availability |
|  | A measure of fault rates should be done during commissioning to establish baseline rates for system reliability monitoring. |

#### 4.2 OTHER CONTROLS AND SOFTWARE REQUIREMENT

| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
| All telescope and instrument parameters are kept in an online database to permit easy implementation of table-driven applications. The interface between software control packages is normally done via interface calls to the on-line database. | Access times to the database are to be in the range of 2-3 msec per access. |
| All telescope, instrument, and detector control information is to be available at any operation level. | Asynchronous writes are to be supported, allowing for concurrent operation. |
| There is to be a consistent and logical (i.e. name based) access method. | Time-access critical information is available in memory. |
| The database must support both remote access and distributed data. | Security control access to system features, possibly restricting some operations to specific remote sites. |
| Support a full implementation of remote operations. includes remote observing, remote control of telescope, enclosure, and instruments, multipoint monitoring, remote monitoring, remote access for testing, development, diagnostics, and maintenance. | All operational capability found in on site operations is extended to remote operations. |
| Final storage locations for the data types are presented here, along with a description of the different databases that are available. | All additional data that is not required on line (configuration information, detailed documentation, operation logs, etc.) are stored in a relational DBMS. (supported software). |
| The on-line data store holds astronomical data for the current observation. | Operational information, such as logging messages, alarms, and errors are special forms of output data, since they are made available for later inspection and debugging. |
| Astronomical data are automatically stored onto the Archive medium (external software). | Replies to commands, including status information, updates to parameters, video and astronomical information are considered data outputs |
| Star catalogs are available in Astronomical object catalogs (external software). | The data end up in different databases. |
| Telescope and instrument parameters are distributed in databases across the IOCs for those systems. There is also a central repository maintained by the OCS that holds these databases for downloading to the IOCs. (developed software). | Operational information, such as logging messages, alarms, and errors are special forms of output data, since they are made available for later inspection and debugging. |
| Input data are all data that are predefined at the operation start. This includes catalogs, calibrations, and flat fields available in archives, etc. | One criteria is that sufficient information be recorded during an observation to recreate the sequence of events that occurred during the observation. This requires that all input and output data be logged appropriately. |
| Observing commands (whether entered interactively or via the Sequencer) are input data that provide information on the course of operation and trigger events. | Normal Operation including Automatic, Interactive, Modes & Control, Performance, Start-up & Shut-down, and Logging |
| Given an initial set of configuration parameters, the Gemini system operates via a sequence of commands. This operation is complemented by using previously stored data, calibrations, and star catalogs. | Operation in failure mode including: Fault-tolerance and recovery, Error logging, Recovery, and Performance. |
| The software interface between workstation and IOC is to use DRAMA’s IMP protocol for all control communication, using SDS as the command structure. | The size of the Gemini system and its long expected lifetime, it is important that standards are provided for system design and development. |
| Interprocess communication on the host workstations is through the same IMP protocol. | All Gemini supported software is to be developed using a formally defined model. The Ward/Mellor approach to developing real-time systems is used and covers: Analysis, Review procedures, Documentation, Coding and debugging, Simulation and testing. |
| Communications between real-time components is based on the EPICS Channel Access protocol. | Functional analysis is done using a CASE tool suitable for use with Ward/Mellor techniques, such as TSEE, by Westmount Technologies |
| Data communications are typically through IMP/SDS. | Real-time support is required at the IOC level. |
| Astronomical data are stored both in the Archiving system and in the data storage subsystem. | Development system software. The choice of a development environment is based on the following criteria: Productivity and development tools, Software portability and hardware independence, Vendor independence, Industry and de facto standards, Support for state-of-the-art user interfaces, Support of a distributed environment |
|  | IOC and cross-development system software. A number of real-time operating systems were examined for compatibility with the goals of the Gemini Project. At the same time, the availability of cross-support development environments was considered. |
|  | The software below the IOC level. There is likely to be software below the IOC level, but it should not require downloading, except possibly for upgrades, typically being placed into ROM or FLASH memory. |
|  | Installation system software. - The host workstation operating system is Unix, currently baselined as Solaris 2.3 or above. |
|  | Communication hardware independence is accomplished by using the DARPA TCP/IP communication protocol over LAN's and the WAN. |
|  | The communication software must support the standard ARPA services (telnet, FTP, SMTP, etc) as well as NFS, RPC, IPC, and the Unix socket interface library |
|  | Hardware Standards |
|  | Sub-Network |
|  | Communication Hardware |
|  | Re-Use Of Existing Software |
|  | Life Cycle Constraints |

#### 4.3 USER REQUIREMENT FOR EPICS DEVELOPERS (Consider as Non-Functional Requirement)

## 5 Commands

### 5.1 Command Routing
| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|Direct routing for command transfer from the Observatory Control System to the subsystems.|Performance in transmitting command of each commands routing.|
|Routing of commands and data through channels.|Security of data in Gemini system.|
|Broadcast for commands and data signal across the control system to the subsystem.|Scalability that relatively to the subsystems required.|

### 5.2 Command Structure
| **Functional** |
| -------------- |
|The “Command_ID” is an identification that is unique to that specific instance of each command.|
|The “Source” and “Target” fields indicate the originator and the recipient for the command, as expected. The Target may be a specific recipient, a Channel, or simply a Broadcast.|
|“Opcode” and “parameter” set to provide the body of the command.|

### 5.3 General Purpose Commands
| **Functional** |
| -------------- |
|Control Commands to all Gemini systems to provide specific functionality during observing. which are presented in the Software Design Descriptions for the individual subsystems. (Status Commands include GetVersion, GetStatus, GetState, GetID, GetConfiguration and Generic Commands include SetStatus, SetConfiguration, RunTest, SetLogging)|

### 5.4 Data Communications
| **Functional** |
| -------------- |
|Communication of information via route that can be direct channels, intelligent channels, or broadcasts across all systems in the Gemini controls network.|
|The commands which allow for establishing synchronization and managing the communication, testing the route, blocking until information is received, and establishing/removing callbacks. (OpenRoute, CloseRoute, ResetRoute, TestRoute, AwaitItem, SetCallback, ClearCallback)|

### 5.5 IOC commands
| **Functional** |
| -------------- |
|**Local Database Access**<br>The commands of Gemini control system and provide access to those databases of the IOC crates in the Gemini system have local databases. Under the Gemini control system, it is expected that the majority of subsystem control is accomplished through these databases. (LocateItem, GetValue, PutValue, StartStream, StopStream, SetEvent, ClearEvent)|
|**Time Handling**<br>The commands for permit appropriate time synchronizations which required among control subsystems. Times will be provided in IRIG-B format as UTC.|
|**Remote DB Access**<br>Communication and database access commands that provide obtainment information to subsystems from other systems within the Gemini control system.|

### 5.6  Command Implementation
| **Functional** | **Non-Functional** |
| -------------- | ------------------ |
|The mappings of specific IOC commands between ASCII strings. |Responsible person for providing procedures for constructing/deconstructing/routing command strings.|
|  |EPICS channel access calls are determined by the individual IOC subsystemdevelopers|
|  |Work Package Descriptions include baseline command descriptions that need to be implemented for that specific work package.|
=======

ITCS431 Software Design and Development(2021)