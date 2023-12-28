export interface Statistic {
  federalStateDataList?: (FederalStateDataListEntity)[] | null;
  depopulationRiskLevel?: null;
}
export interface FederalStateDataListEntity {
  COD: string;
  Nombre: string;
  T3_Unidad: string;
  T3_Escala: string;
  MetaData?: (MetaDataEntity)[] | null;
  Data?: (DataEntity)[] | null;
  extension: number;
  federalStatesExtensionEnum: string;
  regionDataList?: (RegionDataListEntity | null)[] | null;
}
export interface MetaDataEntity {
  Id: number;
  Variable: Variable;
  Nombre: string;
  Codigo: string;
}
export interface Variable {
  Id: number;
  Nombre: string;
  Codigo: string;
}
export interface DataEntity {
  Fecha: string;
  T3_TipoDato: string;
  T3_Periodo: string;
  Anyo: number;
  Valor: number;
  riskLevel: number;
  populationDensity: number;
}
export interface RegionDataListEntity {
  COD: string;
  Nombre: string;
  T3_Unidad: string;
  T3_Escala: string;
  MetaData?: (MetaDataEntity)[] | null;
  Data?: (DataEntity)[] | null;
  extension: number;
  regionExtensionEnum: string;
}
