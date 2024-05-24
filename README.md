# TrackMe-DeliveryIA

Welcome to TrackMe-DeliveryIA, an innovative application developed for a university project focused on enhancing logistics operations for a transportation company in Italy.

## Overview

TrackMe-DeliveryIA is designed to manage the loading and tracking of goods efficiently. It utilizes a ***Genetic Algorithm*** for optimizing package loading and provides real-time tracking capabilities.

## Key Features
+ **Vehicle Management**: Identifies each vehicle by a unique code, type, and container capacity.
+ **Package Management**: Each package is distinguished by a code, sender, recipient, and weight.
+ **Loading Optimization**: Uses a Genetic Algorithm to select the most suitable vehicles based on capacity.
+ **Real-Time Tracking**: Allows recipients to monitor the status of their shipments using unique package codes.

## Technical Details
+ **Initial Setup**: Automatically creates sede.csv and vehicles.csv files through a Bash script on first execution.
+ **Execution Check**: Uses a hidden file .wanted to ensure CSV files are not recreated on subsequent runs.
+ **Platform**: This approach is compatible only with UNIX systems.

## Getting Started
1. **Clone the repository**:
```sh
git clone https://github.com/dangelosimone/TrackMe-DeliveryIA-.git
```
1. **Clone the repository**:
```sh
cd TrackMe-DeliveryIA-
```
1. **Clone the repository**:
```sh
./start.sh
```

## Contributing
Contributions are welcome! Please fork the repository and create a pull request.

##License
This project is licensed under the MIT License.

⚠️ This approach only works on UNIX systems.
