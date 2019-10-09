/*
 * jPOS-Groovy jPOS based project [http://jpos.org]
 * Copyright (C) 2000-2019 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.jpos.iso.ISOMsg
import org.jpos.rc.CMF;
import org.jpos.transaction.Context;
import org.jpos.transaction.ContextConstants
import org.jpos.util.Caller;
import static org.jpos.transaction.TransactionConstants.*;


def req = ContextConstants.REQUEST.toString()
def response = ContextConstants.RESPONSE.toString()
def resp = cfg.get("response", "00")

def r = ctx[req].clone()
r.setResponseMTI()
r.set(39, resp)
ctx[response] = r
ctx.getResult().success (CMF.APPROVED, Caller.info(),"transaction approved")

return PREPARED | NO_JOIN | READONLY

