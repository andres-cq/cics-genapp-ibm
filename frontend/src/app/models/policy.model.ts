export interface Policy {
  policyNumber?: number;
  customerNumber?: number;
  issueDate?: string;
  expiryDate?: string;
  policyType?: string;
  lastChanged?: string;
  brokerId?: number;
  brokersReference?: string;
  payment?: number;
  commission?: number;
  createdAt?: string;
  updatedAt?: string;
  motor?: Motor;
  endowment?: Endowment;
  house?: House;
  commercial?: Commercial;
}

export interface Motor {
  policyNumber?: number;
  make?: string;
  model?: string;
  value?: number;
  regNumber?: string;
  colour?: string;
  cc?: number;
  yearOfManufacture?: string;
  premium?: number;
  accidents?: number;
}

export interface Endowment {
  policyNumber?: number;
  equities?: string;
  withProfits?: string;
  managedFund?: string;
  fundName?: string;
  term?: number;
  sumAssured?: number;
  lifeAssured?: string;
}

export interface House {
  policyNumber?: number;
  propertyType?: string;
  bedrooms?: number;
  value?: number;
  houseName?: string;
  houseNumber?: string;
  postcode?: string;
}

export interface Commercial {
  policyNumber?: number;
  requestDate?: string;
  startDate?: string;
  renewalDate?: string;
  address?: string;
  zipcode?: string;
  latitudeN?: string;
  longitudeW?: string;
  customerName?: string;
  propertyType?: string;
  firePeril?: number;
  firePremium?: number;
  crimePeril?: number;
  crimePremium?: number;
  floodPeril?: number;
  floodPremium?: number;
  weatherPeril?: number;
  weatherPremium?: number;
  status?: number;
  rejectionReason?: string;
}

// Made with Bob
