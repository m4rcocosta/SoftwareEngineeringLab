/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.bankws;

import javax.jws.WebService;

/**
 *
 * @author biar
 */

@WebService
public interface BankIFace {
    public String[] getOperationsByClientID(int ClientID);
    public String getOperationDetailsByID(int OpID);
}
