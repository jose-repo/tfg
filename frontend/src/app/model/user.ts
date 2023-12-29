export interface User {
    id: number | undefined;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    token: string | undefined;
}
