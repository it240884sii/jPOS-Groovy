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
import org.jpos.iso.ISOSource
import org.jpos.space.LocalSpace
import org.jpos.space.SpaceSource

def source   = cfg.get ("source",   SOURCE.toString())
def request =  cfg.get ("request",  REQUEST.toString())
def response = cfg.get ("response", RESPONSE.toString())
def timeout  = cfg.getLong ("timeout", 70000L)
def isp = (LocalSpace) tm.getInputSpace()

def src = ctx[source]
def m = ctx[request]
def resp = ctx[response]
try {
    if (ctx.getResult().hasInhibit()) {
        ctx.log("*** RESPONSE INHIBITED ***")
    } else if (ctx[TX.toString()] != null) {
        ctx.log("*** PANIC - TX not null - RESPONSE OMITTED ***")
    } else if (resp == null) {
        ctx.log (response + " not present")
    } else if (src == null) {
        ctx.log (source + " not present")
    } else if (!src.isConnected())
        ctx.log (source + " is no longer connected")
    else {
        if (src instanceof SpaceSource)
            ((SpaceSource)src).init(isp, timeout)
        if (src.isConnected() && resp != null) {
//            headerStrategy.handleHeader(m, resp);
            src.send(resp)
        }
    }
} catch (Throwable t) {
    ctx.log(t)
}
