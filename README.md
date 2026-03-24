# GenApp - Modernized Insurance Management System

A modernized version of the CICS GenApp (General Insurance Application), transformed from COBOL/CICS/DB2 to a modern Spring Boot and Angular stack.

## 🏗️ Architecture

### Original Application
- **Platform**: IBM CICS Transaction Server for z/OS
- **Language**: COBOL
- **Database**: IBM DB2 for z/OS
- **Interface**: 3270 terminal

### Modernized Application
- **Backend**: Java 25 + Spring Boot 4
- **Frontend**: Angular 21
- **Database**: PostgreSQL 17
- **API**: RESTful services
- **Containerization**: Docker + Docker Compose

## 📋 Features

### Customer Management
- Create, read, update, and delete customer records
- Search customers by name
- View customer details and associated policies

### Policy Management
- Support for 4 policy types:
  - **Motor (M)**: Vehicle insurance policies
  - **Endowment (E)**: Investment-based life insurance
  - **House (H)**: Property insurance
  - **Commercial (C)**: Business property insurance
- Create, read, update, and delete policies
- Link policies to customers
- View policy details by type

### Claims Management
- Create and manage insurance claims
- Associate claims with policies
- Track claim status and payments

## 🚀 Quick Start

### Prerequisites
- Docker 20.10+
- Docker Compose 2.0+
- 8GB RAM minimum
- Ports 80, 8080, and 5432 available

### Running with Docker Compose

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd cics-genapp-ibm
   ```

2. **Start all services**
   ```bash
   docker-compose up -d
   ```

3. **Access the application**
   - Frontend: http://localhost
   - Backend API: http://localhost:8080/api
   - Database: localhost:5432

4. **Stop all services**
   ```bash
   docker-compose down
   ```

5. **Stop and remove all data**
   ```bash
   docker-compose down -v
   ```

## 🛠️ Development Setup

### Backend Development

#### Prerequisites
- Java 25 (JDK)
- Maven 3.9+
- PostgreSQL 17 (or use Docker)

#### Setup
```bash
cd backend

# Start PostgreSQL (if not using Docker)
docker run -d \
  --name genapp-postgres \
  -e POSTGRES_DB=genapp \
  -e POSTGRES_USER=genapp \
  -e POSTGRES_PASSWORD=genapp \
  -p 5432:5432 \
  postgres:17-alpine

# Run the application
mvn spring-boot:run

# Run tests
mvn test

# Build JAR
mvn clean package
```

#### API Endpoints

**Customers**
- `GET /api/customers` - List all customers
- `GET /api/customers/{id}` - Get customer by ID
- `GET /api/customers/search?lastName={name}` - Search customers
- `POST /api/customers` - Create customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

**Policies**
- `GET /api/policies` - List all policies
- `GET /api/policies/{id}` - Get policy by ID
- `GET /api/policies/customer/{customerId}` - Get customer's policies
- `GET /api/policies/type/{type}` - Get policies by type (M/E/H/C)
- `POST /api/policies/motor` - Create motor policy
- `POST /api/policies/endowment` - Create endowment policy
- `POST /api/policies/house` - Create house policy
- `POST /api/policies/commercial` - Create commercial policy
- `PUT /api/policies/{id}` - Update policy
- `DELETE /api/policies/{id}` - Delete policy

**Claims**
- `GET /api/claims` - List all claims
- `GET /api/claims/{id}` - Get claim by ID
- `GET /api/claims/policy/{policyId}` - Get policy's claims
- `POST /api/claims/policy/{policyId}` - Create claim
- `PUT /api/claims/{id}` - Update claim
- `DELETE /api/claims/{id}` - Delete claim

### Frontend Development

#### Prerequisites
- Node.js 20+
- npm 10+

#### Setup
```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm start

# Access at http://localhost:4200

# Build for production
npm run build

# Run tests
npm test
```

## 📊 Database Schema

### Tables
- **customer**: Customer information
- **policy**: Base policy information
- **motor**: Motor policy details
- **endowment**: Endowment policy details
- **house**: House policy details
- **commercial**: Commercial policy details
- **claim**: Insurance claims

### Migrations
Database schema is managed by Flyway. Migrations are located in:
```
backend/src/main/resources/db/migration/
├── V1__create_initial_schema.sql
└── V2__insert_sample_data.sql
```

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test                    # Run all tests
mvn test -Dtest=CustomerServiceTest  # Run specific test
```

### Sample Data
The application includes 10 sample customers and 10 sample policies for testing:
- Customers: IDs 1-10
- Policies: IDs 1-10 (various types)

## 🐳 Docker Configuration

### Backend Dockerfile
- Multi-stage build with Maven
- Uses Eclipse Temurin JRE 25
- Optimized for production

### Frontend Dockerfile
- Multi-stage build with Node.js
- Served with nginx
- Includes API proxy configuration

### Docker Compose Services
- **postgres**: PostgreSQL database
- **backend**: Spring Boot application
- **frontend**: Angular application with nginx

## 🔧 Configuration

### Environment Variables

**Backend** (`backend/src/main/resources/application.yml`):
- `DB_HOST`: Database host (default: localhost)
- `DB_PORT`: Database port (default: 5432)
- `DB_NAME`: Database name (default: genapp)
- `DB_USER`: Database user (default: genapp)
- `DB_PASSWORD`: Database password (default: genapp)

**Frontend** (`frontend/src/environments/environment.ts`):
- `apiUrl`: Backend API URL (default: http://localhost:8080/api)

## 📝 Migration from COBOL

### Key Changes
1. **Data Access**: VSAM files → PostgreSQL database
2. **Business Logic**: COBOL programs → Java services
3. **Presentation**: 3270 screens → Angular web UI
4. **Communication**: CICS LINK → REST API
5. **Transactions**: CICS transactions → HTTP requests

### Preserved Business Logic
- Customer number generation
- Policy type validation (M/E/H/C)
- Referential integrity (customer → policy → claim)
- Two-phase commit semantics (via JPA transactions)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the Eclipse Public License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Original CICS GenApp by IBM Hursley CICS Development team
- Based on IBM SupportPac CB12

## 📞 Support

For issues and questions:
- Check existing issues in the repository
- Review the original CICS GenApp documentation in `base/`
- Consult `AGENTS.md` for development guidelines

## 🗺️ Project Structure

```
cics-genapp-ibm/
├── backend/                 # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/       # Java source code
│   │   │   └── resources/  # Configuration & migrations
│   │   └── test/           # Unit tests
│   ├── Dockerfile
│   └── pom.xml
├── frontend/               # Angular application
│   ├── src/
│   │   ├── app/           # Angular components & services
│   │   ├── assets/        # Static assets
│   │   └── environments/  # Environment configs
│   ├── Dockerfile
│   ├── nginx.conf
│   └── package.json
├── base/                  # Original COBOL application
├── docker-compose.yml     # Docker orchestration
├── AGENTS.md             # Development guidelines
└── README.md             # This file
