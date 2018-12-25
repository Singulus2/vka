import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVkaVka } from 'app/shared/model/vka-vka.model';

type EntityResponseType = HttpResponse<IVkaVka>;
type EntityArrayResponseType = HttpResponse<IVkaVka[]>;

@Injectable({ providedIn: 'root' })
export class VkaVkaService {
    public resourceUrl = SERVER_API_URL + 'api/vkas';

    constructor(protected http: HttpClient) {}

    create(vka: IVkaVka): Observable<EntityResponseType> {
        return this.http.post<IVkaVka>(this.resourceUrl, vka, { observe: 'response' });
    }

    update(vka: IVkaVka): Observable<EntityResponseType> {
        return this.http.put<IVkaVka>(this.resourceUrl, vka, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVkaVka>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVkaVka[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
